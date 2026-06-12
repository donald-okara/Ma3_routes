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

import ke.don.ma3routes.core.domain.model.StageRouteDomain
import ke.don.ma3routes.datasources.local.entities.StageRouteEntity
import ke.don.ma3routes.datasources.remote.model.StageRouteDto
import org.junit.Assert.assertEquals
import org.junit.Test

class StageRouteMappersTest {

    @Test
    fun `StageRouteDto asEntity maps correctly`() {
        val dto = StageRouteDto(
            id = 1L,
            stageId = "s1",
            routeId = "r1",
            role = "boarding",
            confidence = 0.9,
            source = "user",
        )
        val entity = dto.asEntity()
        assertEquals(dto.id, entity.id)
        assertEquals(0.9f, entity.confidence)
    }

    @Test
    fun `StageRouteDto with nulls asEntity uses defaults`() {
        val dto = StageRouteDto(
            id = 1L,
            stageId = "s1",
            routeId = "r1",
            role = null,
            confidence = null,
            source = null,
        )
        val entity = dto.asEntity()
        assertEquals("boarding", entity.role)
        assertEquals(1.0f, entity.confidence)
        assertEquals("system", entity.source)
    }

    @Test
    fun `StageRouteEntity asDomain maps correctly`() {
        val entity = StageRouteEntity(
            id = 1L,
            stageId = "s1",
            routeId = "r1",
            role = "boarding",
            confidence = 0.9f,
            source = "user",
        )
        val domain = entity.asDomain()
        assertEquals(entity.id, domain.id)
        assertEquals(0.9, domain.confidence, 0.0001)
    }

    @Test
    fun `StageRouteDomain asDto maps correctly`() {
        val domain = StageRouteDomain(
            id = 1L,
            stageId = "s1",
            routeId = "r1",
            role = "boarding",
            confidence = 0.9,
            source = "user",
        )
        val dto = domain.asDto()
        assertEquals(domain.id, dto.id)
        assertEquals(0.9, dto.confidence ?: 0.0, 0.0001)
    }
}
