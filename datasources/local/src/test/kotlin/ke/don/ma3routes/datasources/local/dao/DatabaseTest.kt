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
import ke.don.ma3routes.datasources.local.database.Ma3Database
import ke.don.ma3routes.datasources.local.entities.RouteEntity
import ke.don.ma3routes.datasources.local.entities.StageEntity
import ke.don.ma3routes.datasources.local.entities.StageRouteEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var db: Ma3Database
    private lateinit var routeDao: RouteDao
    private lateinit var stageDao: StageDao
    private lateinit var stageRouteDao: StageRouteDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, Ma3Database::class.java
        ).allowMainThreadQueries() // Allowed for tests
            .build()
        routeDao = db.routeDao()
        stageDao = db.stageDao()
        stageRouteDao = db.stageRouteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadRoute() = runTest {
        val route = RouteEntity(id = "route_1", number = "33F", corridor = "Ngong Road")
        routeDao.insert(route)
        val readRoute = routeDao.getRouteById("route_1")
        assertEquals(readRoute?.number, "33F")
    }

    @Test
    @Throws(Exception::class)
    fun insertAndRetrieveRelationship() = runTest {
        val route = RouteEntity(id = "r1", number = "111", corridor = "Ngong")
        val stage = StageEntity(id = "s1", name = "Kencom", area = "CBD", lat = null, lng = null)

        routeDao.insert(route)
        stageDao.insert(stage)

        val relationship = StageRouteEntity(stageId = "s1", routeId = "r1", role = "boarding")
        stageRouteDao.insert(relationship)

        val routesForStage = stageRouteDao.getRoutesForStage("s1").first()
        assertEquals(1, routesForStage.size)
        assertEquals("r1", routesForStage[0].routeId)
    }

    @Test
    @Throws(Exception::class)
    fun testDetailedRoutesForStage() = runTest {
        val route = RouteEntity(id = "r1", number = "111", corridor = "Ngong")
        val stage = StageEntity(id = "s1", name = "Kencom", area = "CBD", lat = null, lng = null)

        routeDao.insert(route)
        stageDao.insert(stage)

        val relationship = StageRouteEntity(stageId = "s1", routeId = "r1", role = "boarding")
        stageRouteDao.insert(relationship)

        val detailedRoutes = stageRouteDao.getDetailedRoutesForStage("s1").first()
        assertEquals(1, detailedRoutes.size)
        assertEquals("111", detailedRoutes[0].number)
    }

    @Test
    @Throws(Exception::class)
    fun testUpsertRoute() = runTest {
        val route = RouteEntity(id = "r1", number = "111", corridor = "Ngong")
        routeDao.insert(route)

        val updatedRoute = routeDao.getRouteById("r1")?.copy(number = "111-B")
        assertNotNull(updatedRoute)
        routeDao.upsert(updatedRoute!!)

        val readRoute = routeDao.getRouteById("r1")
        assertEquals("111-B", readRoute?.number)
    }

    @Test
    @Throws(Exception::class)
    fun testDetailedStagesForRoute() = runTest {
        val route = RouteEntity(id = "r1", number = "111", corridor = "Ngong")
        val stage = StageEntity(id = "s1", name = "Kencom", area = "CBD", lat = null, lng = null)

        routeDao.insert(route)
        stageDao.insert(stage)

        val relationship = StageRouteEntity(stageId = "s1", routeId = "r1", role = "boarding")
        stageRouteDao.insert(relationship)

        val detailedStages = stageRouteDao.getDetailedStagesForRoute("r1").first()
        assertEquals(1, detailedStages.size)
        assertEquals("Kencom", detailedStages[0].name)
    }

    @Test
    @Throws(Exception::class)
    fun testDeleteRelationship() = runTest {
        val route = RouteEntity(id = "r1", number = "111", corridor = "Ngong")
        val stage = StageEntity(id = "s1", name = "Kencom", area = "CBD", lat = null, lng = null)

        routeDao.insert(route)
        stageDao.insert(stage)

        stageRouteDao.insert(StageRouteEntity(stageId = "s1", routeId = "r1"))

        var routes = stageRouteDao.getRoutesForStage("s1").first()
        assertEquals(1, routes.size)

        stageRouteDao.deleteRelationship("s1", "r1")
        routes = stageRouteDao.getRoutesForStage("s1").first()
        assertEquals(0, routes.size)
    }

    @Test
    @Throws(Exception::class)
    fun testGetRouteByNumber() = runTest {
        val route = RouteEntity(id = "r1", number = "46K", corridor = "Kawangware")
        routeDao.insert(route)

        val readRoute = routeDao.getRouteByNumber("46K")
        assertEquals("r1", readRoute?.id)
    }
}
