package ke.don.ma3routes.core.domain.repository

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Result<Unit>

    suspend fun refreshSession(): Result<Unit>
}
