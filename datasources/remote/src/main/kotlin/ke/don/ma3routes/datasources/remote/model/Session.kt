package ke.don.ma3routes.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class Session(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("expires_in") val expiresIn: Long,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("created_at") val createdAt: Long,
    val scope: String,
)
