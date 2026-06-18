package ke.don.ma3routes.datasources.remote.auth

import android.app.Activity
import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import dagger.hilt.android.qualifiers.ApplicationContext
import ke.don.ma3routes.datasources.remote.BuildConfig.WEB_CLIENT_ID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoogleSigninClient @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    private val credentialManager = CredentialManager.create(context)

    private val googleIdOption = GetGoogleIdOption.Builder()
        .setServerClientId(WEB_CLIENT_ID) // IMPORTANT (from Google Cloud Console)
        .setFilterByAuthorizedAccounts(false)
        .build()

    private val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

    suspend fun getCredentialIdToken(context: Context): Result<String> {
        var lastException: Exception? = null
        repeat(5) {
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = context,
                )

                val credential = result.credential

                val googleIdTokenCredential = GoogleIdTokenCredential
                    .createFrom(credential.data)

                val idToken = googleIdTokenCredential.idToken

                return Result.success(idToken)
            } catch (e: NoCredentialException) {
                lastException = e
            } catch (e: Exception) {
                return Result.failure(e)
            }
        }
        return Result.failure(lastException ?: Exception("No credential found after 5 retries"))
    }
}
