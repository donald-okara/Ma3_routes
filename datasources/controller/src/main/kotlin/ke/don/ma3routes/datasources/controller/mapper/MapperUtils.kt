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

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

const val INVALID_TIMESTAMP = -1L
private val remoteFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
private val prettierFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")

/**
 * Extension function to parse a timestamp string into epoch milliseconds.
 * Sample format: 2026-06-01 10:50:27.896745
 */
fun String.toEpochMillis(): Long {
    return try {
        LocalDateTime.parse(this, remoteFormatter)
            .toInstant(ZoneOffset.UTC)
            .toEpochMilli()
    } catch (e: Exception) {
        // Try parsing as human readable if remote fails
        try {
            LocalDateTime.parse(this, prettierFormatter)
                .toInstant(ZoneOffset.UTC)
                .toEpochMilli()
        } catch (e2: Exception) {
            this.toLongOrNull() ?: INVALID_TIMESTAMP
        }
    }
}

/**
 * Extension function to format a timestamp string to a human-readable format.
 */
fun String.toHumanReadable(): String {
    val millis = this.toEpochMillis()
    return if (millis == 0L) this else millis.toHumanReadable()
}

/**
 * Extension function to format epoch milliseconds to a human-readable format.
 */
fun Long.toHumanReadable(): String {
    return try {
        Instant.ofEpochMilli(this)
            .atZone(ZoneId.systemDefault())
            .format(prettierFormatter)
    } catch (e: Exception) {
        this.toString()
    }
}
