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

fun RouteDto.asEntity(): RouteEntity = RouteEntity(
    id = id,
    number = number,
    corridor = corridor,
    createdAt = 0L, // TODO: Parse createdAt string to Long if needed
)

fun RouteEntity.asDomain(): RouteDomain = RouteDomain(
    id = id,
    number = number,
    corridor = corridor,
    createdAt = createdAt.toString(),
)

fun RouteDto.asDomain(): RouteDomain = RouteDomain(
    id = id,
    number = number,
    corridor = corridor,
    createdAt = createdAt,
)

fun RouteDomain.asEntity(): RouteEntity = RouteEntity(
    id = id,
    number = number,
    corridor = corridor,
    createdAt = createdAt.toLongOrNull() ?: 0L,
)

fun RouteDomain.asDto(): RouteDto = RouteDto(
    id = id,
    number = number,
    corridor = corridor,
    createdAt = createdAt,
)
