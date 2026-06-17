package ke.don.ma3routes.core.domain.repository

interface SessionRepository {
    suspend fun signInWithGoogle(idToken: String): Result<Unit>

    suspend fun refreshSession(): Result<Unit>
}
