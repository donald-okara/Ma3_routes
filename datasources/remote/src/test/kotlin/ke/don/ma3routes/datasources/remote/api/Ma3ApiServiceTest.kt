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
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Ma3ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: Ma3ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Ma3ApiService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getRoutes returns list of routes`() = runTest {
        val responseBody = """
            [
                {
                    "id": "route-1",
                    "number": "33F",
                    "corridor": "Ngong",
                    "created_at": "2025-01-01T00:00:00Z"
                },
                {
                    "id": "route-2",
                    "number": "111",
                    "corridor": "Ngong",
                    "created_at": "2025-01-01T00:00:00Z"
                }
            ]
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(responseBody))

        val routes = apiService.getRoutes()

        assertEquals(2, routes.size)
        assertEquals("33F", routes[0].number)
        assertEquals("111", routes[1].number)

        val request = mockWebServer.takeRequest()
        assertEquals("/routes", request.path)
    }

    @Test
    fun `getStages returns list of stages`() = runTest {
        val responseBody = """
            [
                {
                    "id": "stage-1",
                    "name": "Kencom",
                    "area": "CBD",
                    "lat": -1.286389,
                    "lng": 36.821944,
                    "created_at": "2025-01-01T00:00:00Z"
                }
            ]
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(responseBody))

        val stages = apiService.getStages()

        assertEquals(1, stages.size)
        assertEquals("Kencom", stages[0].name)
        assertEquals(-1.286389, stages[0].lat!!, 0.0001)

        val request = mockWebServer.takeRequest()
        assertEquals("/stages", request.path)
    }

    @Test
    fun `getRouteDestinations returns list of destinations`() = runTest {
        val responseBody = """
            [
                {
                    "id": 1,
                    "route_id": "route-1",
                    "destination": "Ngong",
                    "variant": "express",
                    "route_destinations": ["Ngong", "Karen"]
                }
            ]
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(responseBody))

        val destinations = apiService.getRouteDestinations()

        assertEquals(1, destinations.size)
        assertEquals("express", destinations[0].variant)
        assertEquals(2, destinations[0].routeDestinations.size)

        val request = mockWebServer.takeRequest()
        assertEquals("/route-destinations", request.path)
    }

    @Test
    fun `getStageRoutes returns list of stage routes`() = runTest {
        val responseBody = """
            [
                {
                    "id": 1,
                    "stage_id": "stage-1",
                    "route_id": "route-1",
                    "role": "boarding",
                    "confidence": 0.9,
                    "source": "user"
                }
            ]
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(responseBody))

        val stageRoutes = apiService.getStageRoutes()

        assertEquals(1, stageRoutes.size)
        assertEquals("boarding", stageRoutes[0].role)
        assertEquals(0.9, stageRoutes[0].confidence!!, 0.0001)

        val request = mockWebServer.takeRequest()
        assertEquals("/stage-routes", request.path)
    }

    @Test
    fun `getRoute returns a single route`() = runTest {
        val responseBody = """
            {
                "id": "route-1",
                "number": "33F",
                "corridor": "Ngong",
                "created_at": "2025-01-01T00:00:00Z"
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(responseBody))

        val route = apiService.getRoute("route-1")

        assertEquals("route-1", route.id)
        assertEquals("33F", route.number)

        val request = mockWebServer.takeRequest()
        assertEquals("/routes/route-1", request.path)
    }

    @Test
    fun `getStage returns a single stage`() = runTest {
        val responseBody = """
            {
                "id": "stage-1",
                "name": "Kencom",
                "area": "CBD",
                "lat": -1.286389,
                "lng": 36.821944,
                "created_at": "2025-01-01T00:00:00Z"
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(responseBody))

        val stage = apiService.getStage("stage-1")

        assertEquals("stage-1", stage.id)
        assertEquals("Kencom", stage.name)

        val request = mockWebServer.takeRequest()
        assertEquals("/stages/stage-1", request.path)
    }

    @Test
    fun `submitCorrection posts correction and returns it`() = runTest {
        val correctionDto = CorrectionDto(
            id = 1L,
            entityType = "route",
            entityId = "route-1",
            field = "number",
            oldValue = "33",
            newValue = "33F",
            status = "pending",
            createdAt = "2025-01-01T00:00:00Z",
        )
        val responseBody = """
            {
                "id": 1,
                "entity_type": "route",
                "entity_id": "route-1",
                "field": "number",
                "old_value": "33",
                "new_value": "33F",
                "status": "pending",
                "created_at": "2025-01-01T00:00:00Z"
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(responseBody))

        val result = apiService.submitCorrection(correctionDto)

        assertEquals(1L, result.id)
        assertEquals("route-1", result.entityId)
        assertEquals("33F", result.newValue)

        val request = mockWebServer.takeRequest()
        assertEquals("/corrections", request.path)
        assertEquals("POST", request.method)
        val requestBody = request.body.readUtf8()
        assert(requestBody.contains("\"entity_id\":\"route-1\""))
        assert(requestBody.contains("\"new_value\":\"33F\""))
    }

    @Test
    fun `signInWithGoogle parses Supabase session and user`() = runTest {
        val responseBody = """
            {
              "access_token": "access-token",
              "token_type": "bearer",
              "expires_in": 3600,
              "expires_at": 1781778030,
              "refresh_token": "refresh-token",
              "user": {
                "id": "47fb004c-27e3-49db-bc5d-96eea8f1757f",
                "aud": "authenticated",
                "role": "authenticated",
                "email": "user@example.com",
                "email_confirmed_at": "2026-06-18T08:39:17.92258Z",
                "phone": "",
                "confirmed_at": "2026-06-18T08:39:17.92258Z",
                "last_sign_in_at": "2026-06-18T09:20:30.218740725Z",
                "user_metadata": {
                  "avatar_url": "https://example.com/avatar.png",
                  "email": "user@example.com",
                  "email_verified": true,
                  "full_name": "Donald Isoe",
                  "name": "Donald Isoe",
                  "phone_verified": false,
                  "picture": "https://example.com/picture.png",
                  "provider_id": "116728353976016128931",
                  "sub": "116728353976016128931"
                },
                "created_at": "2026-06-18T08:39:17.886394Z",
                "updated_at": "2026-06-18T09:20:30.225226Z",
                "is_anonymous": false
              }
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(responseBody))

        val session = apiService.signInWithGoogle(body = ke.don.ma3routes.datasources.remote.model.GoogleTokenRequest("id-token"))

        assertEquals("access-token", session.accessToken)
        assertEquals("refresh-token", session.refreshToken)
        assertEquals(1781778030L, session.expiresAt)
        assertEquals("47fb004c-27e3-49db-bc5d-96eea8f1757f", session.user.id)
        assertEquals("Donald Isoe", session.user.userMetadata?.fullName)

        val request = mockWebServer.takeRequest()
        assertEquals("/auth/v1/token?grant_type=id_token", request.path)
        assertEquals("POST", request.method)
        assert(request.body.readUtf8().contains("\"id_token\":\"id-token\""))
    }
}
