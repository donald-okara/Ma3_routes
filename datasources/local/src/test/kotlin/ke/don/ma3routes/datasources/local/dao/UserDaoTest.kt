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
import ke.don.ma3routes.datasources.local.entities.UserEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {
    private lateinit var db: Ma3Database
    private lateinit var userDao: UserDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            Ma3Database::class.java,
        ).allowMainThreadQueries()
            .build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun upsertAndReadCurrentUser() = runTest {
        val user = user(id = "user-1", email = "first@example.com")

        userDao.upsert(user)

        assertEquals(user, userDao.getUserById("user-1"))
        assertEquals(user, userDao.getCurrentUser().first())
    }

    @Test
    fun upsertReplacesExistingUser() = runTest {
        userDao.upsert(user(id = "user-1", email = "first@example.com"))

        userDao.upsert(user(id = "user-1", email = "updated@example.com"))

        assertEquals("updated@example.com", userDao.getUserById("user-1")?.email)
    }

    @Test
    fun clearUsersRemovesCurrentUser() = runTest {
        userDao.upsert(user(id = "user-1", email = "first@example.com"))

        userDao.clearUsers()

        assertNull(userDao.getUserById("user-1"))
        assertNull(userDao.getCurrentUser().first())
    }

    private fun user(
        id: String,
        email: String,
    ): UserEntity = UserEntity(
        id = id,
        aud = "authenticated",
        role = "authenticated",
        email = email,
        emailConfirmedAt = "2026-06-18T08:39:17.92258Z",
        phone = "",
        confirmedAt = "2026-06-18T08:39:17.92258Z",
        lastSignInAt = "2026-06-18T09:20:30.218740725Z",
        avatarUrl = "https://example.com/avatar.png",
        fullName = "Donald Isoe",
        name = "Donald Isoe",
        picture = "https://example.com/picture.png",
        providerId = "116728353976016128931",
        providerSubject = "116728353976016128931",
        isEmailVerified = true,
        isPhoneVerified = false,
        isAnonymous = false,
        createdAt = "2026-06-18T08:39:17.886394Z",
        updatedAt = "2026-06-18T09:20:30.225226Z",
    )
}
