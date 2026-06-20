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

import ke.don.ma3routes.core.domain.model.UserDomain
import ke.don.ma3routes.datasources.local.entities.UserEntity
import ke.don.ma3routes.datasources.remote.model.UserDto
import ke.don.ma3routes.datasources.remote.model.UserMetadataDto

fun UserDto.asEntity(): UserEntity = UserEntity(
    id = id,
    aud = aud,
    role = role,
    email = email ?: userMetadata?.email,
    emailConfirmedAt = emailConfirmedAt,
    phone = phone,
    confirmedAt = confirmedAt,
    lastSignInAt = lastSignInAt,
    avatarUrl = userMetadata?.avatarUrl,
    fullName = userMetadata?.fullName,
    name = userMetadata?.name,
    picture = userMetadata?.picture,
    providerId = userMetadata?.providerId,
    providerSubject = userMetadata?.sub,
    isEmailVerified = userMetadata?.emailVerified,
    isPhoneVerified = userMetadata?.phoneVerified,
    isAnonymous = isAnonymous,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun UserDto.asDomain(): UserDomain = UserDomain(
    id = id,
    aud = aud,
    role = role,
    email = email ?: userMetadata?.email,
    emailConfirmedAt = emailConfirmedAt,
    phone = phone,
    confirmedAt = confirmedAt,
    lastSignInAt = lastSignInAt,
    avatarUrl = userMetadata?.avatarUrl,
    fullName = userMetadata?.fullName,
    name = userMetadata?.name,
    picture = userMetadata?.picture,
    providerId = userMetadata?.providerId,
    providerSubject = userMetadata?.sub,
    isEmailVerified = userMetadata?.emailVerified,
    isPhoneVerified = userMetadata?.phoneVerified,
    isAnonymous = isAnonymous,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun UserEntity.asDomain(): UserDomain = UserDomain(
    id = id,
    aud = aud,
    role = role,
    email = email,
    emailConfirmedAt = emailConfirmedAt,
    phone = phone,
    confirmedAt = confirmedAt,
    lastSignInAt = lastSignInAt,
    avatarUrl = avatarUrl,
    fullName = fullName,
    name = name,
    picture = picture,
    providerId = providerId,
    providerSubject = providerSubject,
    isEmailVerified = isEmailVerified,
    isPhoneVerified = isPhoneVerified,
    isAnonymous = isAnonymous,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun UserDomain.asEntity(): UserEntity = UserEntity(
    id = id,
    aud = aud,
    role = role,
    email = email,
    emailConfirmedAt = emailConfirmedAt,
    phone = phone,
    confirmedAt = confirmedAt,
    lastSignInAt = lastSignInAt,
    avatarUrl = avatarUrl,
    fullName = fullName,
    name = name,
    picture = picture,
    providerId = providerId,
    providerSubject = providerSubject,
    isEmailVerified = isEmailVerified,
    isPhoneVerified = isPhoneVerified,
    isAnonymous = isAnonymous,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun UserDomain.asDto(): UserDto = UserDto(
    id = id,
    aud = aud,
    role = role,
    email = email,
    emailConfirmedAt = emailConfirmedAt,
    phone = phone,
    confirmedAt = confirmedAt,
    lastSignInAt = lastSignInAt,
    userMetadata = UserMetadataDto(
        avatarUrl = avatarUrl,
        email = email,
        emailVerified = isEmailVerified,
        fullName = fullName,
        name = name,
        phoneVerified = isPhoneVerified,
        picture = picture,
        providerId = providerId,
        sub = providerSubject,
    ),
    createdAt = createdAt,
    updatedAt = updatedAt,
    isAnonymous = isAnonymous,
)
