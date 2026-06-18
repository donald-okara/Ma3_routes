package ke.don.ma3routes.features.authentication.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.don.koffee.domain.Koffee
import ke.don.koffee.model.ToastType
import ke.don.ma3routes.core.domain.repository.AuthRepository
import ke.don.ma3routes.core.resources.Resources
import ke.don.ma3routes.datasources.remote.auth.GoogleSigninClient
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject constructor(
    private val repository: AuthRepository,
    private val googleSigninClient: GoogleSigninClient,
): ViewModel() {
    fun logIn(context: Context) = viewModelScope.launch {
        googleSigninClient.getCredentialIdToken(context)
            .onSuccess { idToken ->
                repository.signInWithGoogle(idToken)
                    .onSuccess {
                        Koffee.show(
                            title = context.getString(Resources.Strings.authSuccess),
                            description = context.getString(Resources.Strings.authSuccessLoggedIn),
                            type = ToastType.Success
                        )
                    }.onFailure { e ->
                        handleError(e, context)
                    }
            }.onFailure { e ->
                handleError(e, context)
            }
    }

    private fun handleError(e: Throwable, context: Context) {
        Timber.e(e)
        Koffee.show(
            title = context.getString(Resources.Strings.authError),
            description = e.message ?: context.getString(Resources.Strings.authUnknownError),
            type = ToastType.Error,
        )
    }
}
