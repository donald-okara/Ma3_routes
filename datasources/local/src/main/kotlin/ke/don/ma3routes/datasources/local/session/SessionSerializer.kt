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
package ke.don.ma3routes.datasources.local.session

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object SessionSerializer : Serializer<SessionData> {
    override val defaultValue: SessionData = SessionData()

    override suspend fun readFrom(input: InputStream): SessionData {
        val encryptedBytes = input.readBytes()
        if (encryptedBytes.isEmpty()) return defaultValue

        return try {
            val decryptedBytes = Crypto.decrypt(encryptedBytes)
            Json.decodeFromString(SessionData.serializer(), String(decryptedBytes))
        } catch (e: Exception) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: SessionData, output: OutputStream) {
        val jsonString = Json.encodeToString(SessionData.serializer(), t)
        val encryptedBytes = Crypto.encrypt(jsonString.toByteArray())
        withContext(Dispatchers.IO) {
            output.write(encryptedBytes)
        }
    }
}
