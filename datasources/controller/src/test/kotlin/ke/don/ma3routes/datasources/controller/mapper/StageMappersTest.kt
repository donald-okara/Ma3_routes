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

import ke.don.ma3routes.core.domain.model.StageDomain
import ke.don.ma3routes.datasources.local.entities.StageEntity
import ke.don.ma3routes.datasources.remote.model.StageDto
import org.junit.Assert.assertEquals
import org.junit.Test

class StageMappersTest {

    @Test
    fun `StageDto asEntity maps correctly`() {
        val dto = StageDto(
            id = "s1",
            name = "Kencom",
            area = "CBD",
            lat = -1.28,
            lng = 36.82,
            createdAt = "2026-06-01 10:50:27.896745",
        )
        val entity = dto.asEntity()
        assertEquals(dto.id, entity.id)
        assertEquals(dto.name, entity.name)
        assertEquals(dto.lat, entity.lat)
        assert(entity.createdAt > 0L)
    }

    @Test
    fun `StageEntity asDomain maps correctly`() {
        val entity = StageEntity(
            id = "s1",
            name = "Kencom",
            area = "CBD",
            lat = -1.28,
            lng = 36.82,
            createdAt = 123456789L,
        )
        val domain = entity.asDomain()
        assertEquals(entity.id, domain.id)
        assertEquals(entity.name, domain.name)
        assert(domain.createdAt.isNotEmpty())
    }

    @Test
    fun `StageDomain asDto maps correctly`() {
        val domain = StageDomain(
            id = "s1",
            name = "Kencom",
            area = "CBD",
            lat = -1.28,
            lng = 36.82,
            createdAt = "01 Jun 2026, 10:50",
        )
        val dto = domain.asDto()
        assertEquals(domain.id, dto.id)
        assertEquals(domain.name, dto.name)
        assertEquals("01 Jun 2026, 10:50", dto.createdAt)
    }
}
