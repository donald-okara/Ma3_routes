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
package ke.don.ma3routes.core.domain.model

/**
 * Domain representation of a User.
 */
data class UserDomain(
    val id: String = "",
    val aud: String? = null,
    val role: String? = null,
    val email: String? = null,
    val emailConfirmedAt: String? = null,
    val phone: String? = null,
    val confirmedAt: String? = null,
    val lastSignInAt: String? = null,
    val avatarUrl: String? = null,
    val fullName: String? = null,
    val name: String? = null,
    val picture: String? = null,
    val providerId: String? = null,
    val providerSubject: String? = null,
    val isEmailVerified: Boolean? = null,
    val isPhoneVerified: Boolean? = null,
    val isAnonymous: Boolean = false,
    val createdAt: String? = null,
    val updatedAt: String? = null,
)
