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
            createdAt = "2025-01-01"
        )
        val entity = dto.asEntity()
        assertEquals(dto.id, entity.id)
        assertEquals(dto.newValue, entity.newValue)
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
            createdAt = 123456789L
        )
        val domain = entity.asDomain()
        assertEquals(entity.id, domain.id)
        assertEquals("123456789", domain.createdAt)
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
            createdAt = "2025-01-01"
        )
        val dto = domain.asDto()
        assertEquals(domain.id, dto.id)
        assertEquals(domain.createdAt, dto.createdAt)
    }
}
