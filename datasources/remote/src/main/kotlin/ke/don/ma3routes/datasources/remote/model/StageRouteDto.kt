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
package ke.don.ma3routes.datasources.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Network representation of the relationship between stages and routes.
 */
data class StageRouteDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("stage_id")
    val stageId: String,
    @SerializedName("route_id")
    val routeId: String,
    @SerializedName("role")
    val role: String = "boarding",
    @SerializedName("confidence")
    val confidence: Double = 1.0,
    @SerializedName("source")
    val source: String = "system",
)
