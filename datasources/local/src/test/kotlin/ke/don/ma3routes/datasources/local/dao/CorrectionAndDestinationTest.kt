/*
 * Copyright 2025 Donald Isoe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ke.don.ma3routes.datasources.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import java.io.IOException
import ke.don.ma3routes.datasources.local.database.Ma3Database
import ke.don.ma3routes.datasources.local.entities.CorrectionEntity
import ke.don.ma3routes.datasources.local.entities.RouteDestinationEntity
import ke.don.ma3routes.datasources.local.entities.RouteEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CorrectionAndDestinationTest {
    private lateinit var db: Ma3Database
    private lateinit var correctionDao: CorrectionDao
    private lateinit var routeDao: RouteDao
    private lateinit var destinationDao: RouteDestinationDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            Ma3Database::class.java,
        ).allowMainThreadQueries()
            .build()
        correctionDao = db.correctionDao()
        routeDao = db.routeDao()
        destinationDao = db.routeDestinationDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun testCorrectionInsertAndQuery() = runTest {
        val correction = CorrectionEntity(
            entityType = "route",
            entityId = "r1",
            field = "number",
            oldValue = "33",
            newValue = "33F",
        )
        correctionDao.insert(correction)

        val pending = correctionDao.getCorrectionsByStatus("pending").first()
        assertEquals(1, pending.size)
        assertEquals("33F", pending[0].newValue)
    }

    @Test
    fun testRouteDestinationsWithConverter() = runTest {
        val route = RouteEntity(id = "r1", number = "111", corridor = "Ngong")
        routeDao.insert(route)

        val destination = RouteDestinationEntity(
            routeId = "r1",
            destination = "Dagoretti",
            variant = "express",
            destinations = listOf("Prestige", "Adams", "Dagoretti Corner"),
        )
        destinationDao.insert(destination)

        val result = destinationDao.getDestinationsForRoute("r1").first()
        assertEquals(1, result.size)
        assertEquals(3, result[0].destinations.size)
        assertEquals("Adams", result[0].destinations[1])
    }
}
