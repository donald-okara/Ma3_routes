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
        assertEquals(0.9, stageRoutes[0].confidence, 0.0001)

        val request = mockWebServer.takeRequest()
        assertEquals("/stage-routes", request.path)
    }
}
