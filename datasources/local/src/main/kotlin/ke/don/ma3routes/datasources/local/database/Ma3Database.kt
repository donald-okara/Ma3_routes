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
package ke.don.ma3routes.datasources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ke.don.ma3routes.datasources.local.dao.CorrectionDao
import ke.don.ma3routes.datasources.local.dao.RouteDao
import ke.don.ma3routes.datasources.local.dao.RouteDestinationDao
import ke.don.ma3routes.datasources.local.dao.StageDao
import ke.don.ma3routes.datasources.local.dao.StageRouteDao
import ke.don.ma3routes.datasources.local.entities.CorrectionEntity
import ke.don.ma3routes.datasources.local.entities.RouteDestinationEntity
import ke.don.ma3routes.datasources.local.entities.RouteEntity
import ke.don.ma3routes.datasources.local.entities.StageEntity
import ke.don.ma3routes.datasources.local.entities.StageRouteEntity

@Database(
    entities = [
        RouteEntity::class,
        StageEntity::class,
        RouteDestinationEntity::class,
        StageRouteEntity::class,
        CorrectionEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class Ma3Database : RoomDatabase() {
    abstract fun routeDao(): RouteDao
    abstract fun stageDao(): StageDao
    abstract fun routeDestinationDao(): RouteDestinationDao
    abstract fun stageRouteDao(): StageRouteDao
    abstract fun correctionDao(): CorrectionDao
}
