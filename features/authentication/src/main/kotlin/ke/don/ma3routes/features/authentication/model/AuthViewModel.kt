package ke.don.ma3routes.features.authentication.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.don.koffee.domain.Koffee
import ke.don.koffee.model.ToastType
import ke.don.ma3routes.core.domain.repository.AuthRepository
import ke.don.ma3routes.core.domain.util.ResultStatus
import ke.don.ma3routes.core.domain.util.isLoading
import ke.don.ma3routes.core.resources.Resources
import ke.don.ma3routes.datasources.remote.auth.GoogleSigninClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject constructor(
    private val repository: AuthRepository,
    private val googleSigninClient: GoogleSigninClient,
): ViewModel() {
    private val _authState = MutableStateFlow(AuthUiState())
    val authState = _authState.asStateFlow()

    fun logIn(context: Context) = viewModelScope.launch {
        _authState.update { state ->
            state.copy(
                status = ResultStatus.Loading
            )
        }
        googleSigninClient.getCredentialIdToken(context)
            .onSuccess { idToken ->
                repository.signInWithGoogle(idToken)
                    .onSuccess {
                        _authState.update { state ->
                            state.copy(
                                status = ResultStatus.Success
                            )
                        }
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
        _authState.update { state ->
            state.copy(
                status = ResultStatus.Error(e.message ?: context.getString(Resources.Strings.authUnknownError))
            )
        }

        Timber.e(e)
        Koffee.show(
            title = context.getString(Resources.Strings.authError),
            description = e.message ?: context.getString(Resources.Strings.authUnknownError),
            type = ToastType.Error,
        )
    }
}
