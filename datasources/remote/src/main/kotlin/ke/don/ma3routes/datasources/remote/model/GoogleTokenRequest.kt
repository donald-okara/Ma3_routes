package ke.don.ma3routes.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class GoogleTokenRequest(
    @SerializedName("id_token") val idToken: String,
    val provider: String = "google"
)
