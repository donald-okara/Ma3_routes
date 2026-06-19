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
package ke.don.ma3routes.datasources.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ke.don.ma3routes.core.domain.session.SessionManager
import ke.don.ma3routes.datasources.local.session.SessionData
import ke.don.ma3routes.datasources.local.session.SessionManagerImpl
import ke.don.ma3routes.datasources.local.session.SessionSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SessionModule {

    @Binds
    @Singleton
    abstract fun bindSessionManager(sessionManagerImpl: SessionManagerImpl): SessionManager

    companion object {
        private const val SESSION_DATA_FILE = "session_data.pb"

        @Provides
        @Singleton
        fun provideSessionDataStore(@ApplicationContext context: Context): DataStore<SessionData> {
            return DataStoreFactory.create(
                serializer = SessionSerializer,
                produceFile = { context.dataStoreFile(SESSION_DATA_FILE) },
                scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
            )
        }
    }
}
