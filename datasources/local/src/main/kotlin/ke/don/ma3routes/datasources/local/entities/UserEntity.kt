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
package ke.don.ma3routes.datasources.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "aud")
    val aud: String?,
    @ColumnInfo(name = "role")
    val role: String?,
    @ColumnInfo(name = "email")
    val email: String?,
    @ColumnInfo(name = "email_confirmed_at")
    val emailConfirmedAt: String?,
    @ColumnInfo(name = "phone")
    val phone: String?,
    @ColumnInfo(name = "confirmed_at")
    val confirmedAt: String?,
    @ColumnInfo(name = "last_sign_in_at")
    val lastSignInAt: String?,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String?,
    @ColumnInfo(name = "full_name")
    val fullName: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "picture")
    val picture: String?,
    @ColumnInfo(name = "provider_id")
    val providerId: String?,
    @ColumnInfo(name = "provider_subject")
    val providerSubject: String?,
    @ColumnInfo(name = "is_email_verified")
    val isEmailVerified: Boolean?,
    @ColumnInfo(name = "is_phone_verified")
    val isPhoneVerified: Boolean?,
    @ColumnInfo(name = "is_anonymous")
    val isAnonymous: Boolean,
    @ColumnInfo(name = "created_at")
    val createdAt: String?,
    @ColumnInfo(name = "updated_at")
    val updatedAt: String?,
)
