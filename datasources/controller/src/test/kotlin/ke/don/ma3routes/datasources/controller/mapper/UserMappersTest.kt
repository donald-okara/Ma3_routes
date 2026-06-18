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

import ke.don.ma3routes.datasources.remote.model.UserDto
import ke.don.ma3routes.datasources.remote.model.UserMetadataDto
import org.junit.Assert.assertEquals
import org.junit.Test

class UserMappersTest {

    @Test
    fun `UserDto asEntity maps auth response fields`() {
        val dto = UserDto(
            id = "47fb004c-27e3-49db-bc5d-96eea8f1757f",
            aud = "authenticated",
            role = "authenticated",
            email = "user@example.com",
            emailConfirmedAt = "2026-06-18T08:39:17.92258Z",
            phone = "",
            confirmedAt = "2026-06-18T08:39:17.92258Z",
            lastSignInAt = "2026-06-18T09:20:30.218740725Z",
            userMetadata = UserMetadataDto(
                avatarUrl = "https://example.com/avatar.png",
                email = "metadata@example.com",
                emailVerified = true,
                fullName = "Donald Isoe",
                name = "Donald Isoe",
                phoneVerified = false,
                picture = "https://example.com/picture.png",
                providerId = "116728353976016128931",
                sub = "116728353976016128931",
            ),
            createdAt = "2026-06-18T08:39:17.886394Z",
            updatedAt = "2026-06-18T09:20:30.225226Z",
            isAnonymous = false,
        )

        val entity = dto.asEntity()

        assertEquals(dto.id, entity.id)
        assertEquals("user@example.com", entity.email)
        assertEquals("https://example.com/avatar.png", entity.avatarUrl)
        assertEquals("Donald Isoe", entity.fullName)
        assertEquals("116728353976016128931", entity.providerId)
        assertEquals("116728353976016128931", entity.providerSubject)
        assertEquals(true, entity.isEmailVerified)
        assertEquals(false, entity.isPhoneVerified)
        assertEquals(false, entity.isAnonymous)
    }

    @Test
    fun `UserDto asEntity falls back to metadata email`() {
        val dto = UserDto(
            id = "user-1",
            aud = null,
            role = null,
            email = null,
            emailConfirmedAt = null,
            phone = null,
            confirmedAt = null,
            lastSignInAt = null,
            userMetadata = UserMetadataDto(
                avatarUrl = null,
                email = "metadata@example.com",
                emailVerified = null,
                fullName = null,
                name = null,
                phoneVerified = null,
                picture = null,
                providerId = null,
                sub = null,
            ),
            createdAt = null,
            updatedAt = null,
            isAnonymous = false,
        )

        val entity = dto.asEntity()

        assertEquals("metadata@example.com", entity.email)
    }
}
