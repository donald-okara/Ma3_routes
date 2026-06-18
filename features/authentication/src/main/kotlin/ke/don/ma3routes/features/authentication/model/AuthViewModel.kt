package ke.don.ma3routes.features.authentication.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.don.koffee.domain.Koffee
import ke.don.koffee.model.ToastType
import ke.don.ma3routes.core.domain.repository.AuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject constructor(
    private val repository: AuthRepository
): ViewModel() {
    fun logIn() = viewModelScope.launch {
        repository.signInWithGoogle()
            .onSuccess {
                Koffee.show(
                    title = "Success",
                    description = "Successfully logged in",
                    type = ToastType.Success
                )
            }.onFailure {
                Koffee.show(
                    title = "Error",
                    description = it.message ?: "Unknown error",
                    type = ToastType.Error
                )
            }
    }
}
