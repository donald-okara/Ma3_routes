package ke.don.ma3routes.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class Session(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("expires_in") val expiresIn: Long,
    @SerializedName("expires_at") val expiresAt: Long?,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("user") val user: UserDto,
)

data class UserDto(
    @SerializedName("id") val id: String,
    @SerializedName("aud") val aud: String?,
    @SerializedName("role") val role: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("email_confirmed_at") val emailConfirmedAt: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("confirmed_at") val confirmedAt: String?,
    @SerializedName("last_sign_in_at") val lastSignInAt: String?,
    @SerializedName("user_metadata") val userMetadata: UserMetadataDto?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("is_anonymous") val isAnonymous: Boolean,
)

data class UserMetadataDto(
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("email_verified") val emailVerified: Boolean?,
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("phone_verified") val phoneVerified: Boolean?,
    @SerializedName("picture") val picture: String?,
    @SerializedName("provider_id") val providerId: String?,
    @SerializedName("sub") val sub: String?,
)
