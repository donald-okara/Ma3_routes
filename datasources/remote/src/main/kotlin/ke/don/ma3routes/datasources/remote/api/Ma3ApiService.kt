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
package ke.don.ma3routes.datasources.remote.api

import ke.don.ma3routes.datasources.remote.model.CorrectionDto
import ke.don.ma3routes.datasources.remote.model.GoogleTokenRequest
import ke.don.ma3routes.datasources.remote.model.RouteDestinationDto
import ke.don.ma3routes.datasources.remote.model.RouteDto
import ke.don.ma3routes.datasources.remote.model.Session
import ke.don.ma3routes.datasources.remote.model.StageDto
import ke.don.ma3routes.datasources.remote.model.StageRouteDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Ma3ApiService {
    @POST("/auth/v1/token")
    suspend fun signInWithGoogle(
        @Query("grant_type") grantType: String = "id_token",
        @Body body: GoogleTokenRequest
    ): Session

    @POST("/auth/v1/token")
    suspend fun refreshToken(
        @Query("grant_type") grantType: String = "refresh_token",
        @Query("refresh_token") refreshToken: String
    ): Session

    @GET("routes")
    @ApplyInterceptors(InterceptorType.API_KEY)
    suspend fun getRoutes(): List<RouteDto>

    @GET("routes/{id}")
    @ApplyInterceptors(InterceptorType.API_KEY)
    suspend fun getRoute(@Path("id") id: String): RouteDto

    @GET("stages")
    @ApplyInterceptors(InterceptorType.API_KEY)
    suspend fun getStages(): List<StageDto>

    @GET("stages/{id}")
    @ApplyInterceptors(InterceptorType.API_KEY)
    suspend fun getStage(@Path("id") id: String): StageDto

    @GET("route-destinations")
    @ApplyInterceptors(InterceptorType.API_KEY)
    suspend fun getRouteDestinations(): List<RouteDestinationDto>

    @GET("stage-routes")
    @ApplyInterceptors(InterceptorType.API_KEY)
    suspend fun getStageRoutes(): List<StageRouteDto>

    @POST("corrections")
    @ApplyInterceptors(InterceptorType.API_KEY, InterceptorType.JSON_CONTENT_TYPE)
    suspend fun submitCorrection(@Body correction: CorrectionDto): CorrectionDto
}
