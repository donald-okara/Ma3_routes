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

fun CorrectionDto.asEntity(): CorrectionEntity = CorrectionEntity(
    id = id,
    entityType = entityType,
    entityId = entityId,
    field = field,
    oldValue = oldValue,
    newValue = newValue,
    status = status,
    createdAt = 0L, // TODO: Parse createdAt string to Long
)

fun CorrectionEntity.asDomain(): CorrectionDomain = CorrectionDomain(
    id = id,
    entityType = entityType,
    entityId = entityId,
    field = field,
    oldValue = oldValue,
    newValue = newValue,
    status = status,
    createdAt = createdAt.toString(),
)

fun CorrectionDto.asDomain(): CorrectionDomain = CorrectionDomain(
    id = id,
    entityType = entityType,
    entityId = entityId,
    field = field,
    oldValue = oldValue,
    newValue = newValue,
    status = status,
    createdAt = createdAt,
)

fun CorrectionDomain.asEntity(): CorrectionEntity = CorrectionEntity(
    id = id,
    entityType = entityType,
    entityId = entityId,
    field = field,
    oldValue = oldValue,
    newValue = newValue,
    status = status,
    createdAt = createdAt.toLongOrNull() ?: 0L,
)

fun CorrectionDomain.asDto(): CorrectionDto = CorrectionDto(
    id = id,
    entityType = entityType,
    entityId = entityId,
    field = field,
    oldValue = oldValue,
    newValue = newValue,
    status = status,
    createdAt = createdAt,
)
