package ke.don.ma3routes.core.domain.repository

import android.content.Context

interface AuthRepository {
    suspend fun login(context: Context): Result<Unit>

    suspend fun signInWithGoogle(idToken: String): Result<Unit>

    suspend fun signOut(): Result<Unit>
}
