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

import ke.don.ma3routes.core.domain.model.CorrectionDomain
import ke.don.ma3routes.datasources.local.entities.CorrectionEntity
import ke.don.ma3routes.datasources.remote.model.CorrectionDto
import org.junit.Assert.assertEquals
import org.junit.Test

class CorrectionMappersTest {

    @Test
    fun `CorrectionDto asEntity maps correctly`() {
        val dto = CorrectionDto(
            id = 1L,
            entityType = "route",
            entityId = "r1",
            field = "number",
            oldValue = "33",
            newValue = "33F",
            status = "pending",
            createdAt = "2026-06-01 10:50:27.896745",
        )
        val entity = dto.asEntity()
        assertEquals(dto.id, entity.id)
        assertEquals(dto.newValue, entity.newValue)
        // Verify it's not 0L, meaning it parsed something
        assert(entity.createdAt > 0L)
    }

    @Test
    fun `CorrectionEntity asDomain maps correctly`() {
        val entity = CorrectionEntity(
            id = 1L,
            entityType = "route",
            entityId = "r1",
            field = "number",
            oldValue = "33",
            newValue = "33F",
            status = "pending",
            createdAt = 123456789L,
        )
        val domain = entity.asDomain()
        assertEquals(entity.id, domain.id)
        assert(domain.createdAt.isNotEmpty())
    }

    @Test
    fun `CorrectionDomain asDto maps correctly`() {
        val domain = CorrectionDomain(
            id = 1L,
            entityType = "route",
            entityId = "r1",
            field = "number",
            oldValue = "33",
            newValue = "33F",
            status = "pending",
            createdAt = "01 Jun 2026, 10:50",
        )
        val dto = domain.asDto()
        assertEquals(domain.id, dto.id)
        assertEquals("01 Jun 2026, 10:50", dto.createdAt)
    }
}
