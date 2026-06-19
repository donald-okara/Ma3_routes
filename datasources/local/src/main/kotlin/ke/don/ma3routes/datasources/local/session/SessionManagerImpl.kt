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
package ke.don.ma3routes.datasources.local.session

import androidx.datastore.core.DataStore
import ke.don.ma3routes.core.domain.session.SessionManager
import ke.don.ma3routes.datasources.local.dao.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionManagerImpl @Inject constructor(
    private val dataStore: DataStore<SessionData>,
    private val dao: UserDao
) : SessionManager {

    override fun getAccessToken(): Flow<String?> = dataStore.data.map { it.accessToken }

    override fun getRefreshToken(): Flow<String?> = dataStore.data.map { it.refreshToken }

    override suspend fun saveSession(accessToken: String, refreshToken: String) {
        dataStore.updateData { currentSession ->
            currentSession.copy(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }
    }

    override suspend fun clearSession() {
        dao.clearUsers()
        dataStore.updateData {
            SessionData()
        }
    }
}
