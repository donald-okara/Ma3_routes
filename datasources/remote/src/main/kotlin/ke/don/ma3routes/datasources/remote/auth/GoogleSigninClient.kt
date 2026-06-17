package ke.don.ma3routes.datasources.remote.auth

import android.app.Activity
import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import ke.don.ma3routes.datasources.remote.BuildConfig.WEB_CLIENT_ID
import javax.inject.Inject

@ActivityScoped
class GoogleSigninClient @Inject constructor() {
    @Inject
    lateinit var activity: Activity

    val credentialManager = CredentialManager.create(activity)

    val googleIdOption = GetGoogleIdOption.Builder()
        .setServerClientId(WEB_CLIENT_ID) // IMPORTANT (from Google Cloud Console)
        .setFilterByAuthorizedAccounts(false)
        .build()

    val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

    suspend fun getCredentialIdToken(): Result<String> {
        try {
            val result = credentialManager.getCredential(
                request = request,
                context = activity,
            )

            val credential = result.credential

            val googleIdTokenCredential = GoogleIdTokenCredential
                .createFrom(credential.data)

            val idToken = googleIdTokenCredential.idToken

            return Result.success(idToken)
        }
        catch (e: Exception) {
            return Result.failure(e)
        }
    }
}
