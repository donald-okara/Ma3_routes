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
package ke.don.ma3routes.datasources.controller.mapper

import ke.don.ma3routes.core.domain.model.RouteDestinationDomain
import ke.don.ma3routes.datasources.local.entities.RouteDestinationEntity
import ke.don.ma3routes.datasources.remote.model.RouteDestinationDto
import org.junit.Assert.assertEquals
import org.junit.Test

class RouteDestinationMappersTest {

    @Test
    fun `RouteDestinationDto asEntity maps correctly`() {
        val dto = RouteDestinationDto(
            id = 1L,
            routeId = "r1",
            destination = "Ngong",
            variant = "express",
            routeDestinations = listOf("A", "B")
        )
        val entity = dto.asEntity()
        assertEquals(dto.id, entity.id)
        assertEquals(dto.routeId, entity.routeId)
        assertEquals(dto.routeDestinations, entity.destinations)
    }

    @Test
    fun `RouteDestinationEntity asDomain maps correctly`() {
        val entity = RouteDestinationEntity(
            id = 1L,
            routeId = "r1",
            destination = "Ngong",
            variant = "express",
            destinations = listOf("A", "B")
        )
        val domain = entity.asDomain()
        assertEquals(entity.id, domain.id)
        assertEquals(entity.destinations, domain.routeDestinations)
    }

    @Test
    fun `RouteDestinationDomain asDto maps correctly`() {
        val domain = RouteDestinationDomain(
            id = 1L,
            routeId = "r1",
            destination = "Ngong",
            variant = "express",
            routeDestinations = listOf("A", "B")
        )
        val dto = domain.asDto()
        assertEquals(domain.id, dto.id)
        assertEquals(domain.routeDestinations, dto.routeDestinations)
    }
}
