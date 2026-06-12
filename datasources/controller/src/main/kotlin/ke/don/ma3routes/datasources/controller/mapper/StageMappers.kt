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

fun StageDto.asEntity(): StageEntity = StageEntity(
    id = id,
    name = name,
    area = area,
    lat = lat,
    lng = lng,
    createdAt = createdAt.toEpochMillis(),
)

fun StageEntity.asDomain(): StageDomain = StageDomain(
    id = id,
    name = name,
    area = area,
    lat = lat,
    lng = lng,
    createdAt = createdAt.toHumanReadable(),
)

fun StageDto.asDomain(): StageDomain = StageDomain(
    id = id,
    name = name,
    area = area,
    lat = lat,
    lng = lng,
    createdAt = createdAt.toHumanReadable(),
)

fun StageDomain.asEntity(): StageEntity = StageEntity(
    id = id,
    name = name,
    area = area,
    lat = lat,
    lng = lng,
    createdAt = createdAt.toEpochMillis(),
)

fun StageDomain.asDto(): StageDto = StageDto(
    id = id,
    name = name,
    area = area,
    lat = lat,
    lng = lng,
    createdAt = createdAt,
)
