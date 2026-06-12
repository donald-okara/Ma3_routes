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

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ke.don.ma3routes.datasources.local.entities.RouteEntity
import ke.don.ma3routes.datasources.local.entities.StageEntity
import ke.don.ma3routes.datasources.local.entities.StageRouteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StageRouteDao : BaseDao<StageRouteEntity> {

    @Query("SELECT * FROM stage_routes WHERE stage_id = :stageId")
    fun getRoutesForStage(stageId: String): Flow<List<StageRouteEntity>>

    @Query("SELECT * FROM stage_routes WHERE route_id = :routeId")
    fun getStagesForRoute(routeId: String): Flow<List<StageRouteEntity>>

    @Transaction
    @Query("""
        SELECT * FROM routes
        INNER JOIN stage_routes ON routes.id = stage_routes.route_id
        WHERE stage_routes.stage_id = :stageId
    """)
    fun getDetailedRoutesForStage(stageId: String): Flow<List<RouteEntity>>

    @Transaction
    @Query("""
        SELECT * FROM stages
        INNER JOIN stage_routes ON stages.id = stage_routes.stage_id
        WHERE stage_routes.route_id = :routeId
    """)
    fun getDetailedStagesForRoute(routeId: String): Flow<List<StageEntity>>

    @Query("DELETE FROM stage_routes WHERE stage_id = :stageId AND route_id = :routeId")
    suspend fun deleteRelationship(stageId: String, routeId: String)

    @Query("DELETE FROM stage_routes WHERE stage_id = :stageId")
    suspend fun deleteAllRoutesForStage(stageId: String)

    @Query("DELETE FROM stage_routes WHERE route_id = :routeId")
    suspend fun deleteAllStagesForRoute(routeId: String)
}
