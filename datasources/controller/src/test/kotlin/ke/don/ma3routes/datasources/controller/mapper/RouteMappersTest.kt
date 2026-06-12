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

import ke.don.ma3routes.core.domain.model.RouteDomain
import ke.don.ma3routes.datasources.local.entities.RouteEntity
import ke.don.ma3routes.datasources.remote.model.RouteDto
import org.junit.Assert.assertEquals
import org.junit.Test

class RouteMappersTest {

    @Test
    fun `RouteDto asEntity maps correctly`() {
        val dto = RouteDto(
            id = "1",
            number = "33",
            corridor = "Ngong",
            createdAt = "2025-01-01"
        )
        val entity = dto.asEntity()
        assertEquals(dto.id, entity.id)
        assertEquals(dto.number, entity.number)
        assertEquals(dto.corridor, entity.corridor)
    }

    @Test
    fun `RouteEntity asDomain maps correctly`() {
        val entity = RouteEntity(
            id = "1",
            number = "33",
            corridor = "Ngong",
            createdAt = 123456789L
        )
        val domain = entity.asDomain()
        assertEquals(entity.id, domain.id)
        assertEquals(entity.number, domain.number)
        assertEquals(entity.corridor, domain.corridor)
        assertEquals("123456789", domain.createdAt)
    }

    @Test
    fun `RouteDto asDomain maps correctly`() {
        val dto = RouteDto(
            id = "1",
            number = "33",
            corridor = "Ngong",
            createdAt = "2025-01-01"
        )
        val domain = dto.asDomain()
        assertEquals(dto.id, domain.id)
        assertEquals(dto.number, domain.number)
        assertEquals(dto.corridor, domain.corridor)
        assertEquals(dto.createdAt, domain.createdAt)
    }

    @Test
    fun `RouteDomain asEntity maps correctly`() {
        val domain = RouteDomain(
            id = "1",
            number = "33",
            corridor = "Ngong",
            createdAt = "123456789"
        )
        val entity = domain.asEntity()
        assertEquals(domain.id, entity.id)
        assertEquals(domain.number, entity.number)
        assertEquals(domain.corridor, entity.corridor)
        assertEquals(123456789L, entity.createdAt)
    }

    @Test
    fun `RouteDomain asDto maps correctly`() {
        val domain = RouteDomain(
            id = "1",
            number = "33",
            corridor = "Ngong",
            createdAt = "2025-01-01"
        )
        val dto = domain.asDto()
        assertEquals(domain.id, dto.id)
        assertEquals(domain.number, dto.number)
        assertEquals(domain.corridor, dto.corridor)
        assertEquals(domain.createdAt, dto.createdAt)
    }
}
