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
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import ke.don.ma3routes.datasources.local.dao.CorrectionDao
import ke.don.ma3routes.datasources.local.dao.RouteDao
import ke.don.ma3routes.datasources.local.dao.RouteDestinationDao
import ke.don.ma3routes.datasources.local.dao.StageDao
import ke.don.ma3routes.datasources.local.dao.StageRouteDao
import ke.don.ma3routes.datasources.local.database.Ma3Database

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMa3Database(@ApplicationContext context: Context): Ma3Database =
        Room.databaseBuilder(
            context,
            Ma3Database::class.java,
            "ma3-database",
        ).build()

    @Provides
    fun provideRouteDao(database: Ma3Database): RouteDao = database.routeDao()

    @Provides
    fun provideStageDao(database: Ma3Database): StageDao = database.stageDao()

    @Provides
    fun provideRouteDestinationDao(database: Ma3Database): RouteDestinationDao =
        database.routeDestinationDao()

    @Provides
    fun provideStageRouteDao(database: Ma3Database): StageRouteDao = database.stageRouteDao()

    @Provides
    fun provideCorrectionDao(database: Ma3Database): CorrectionDao = database.correctionDao()
}
