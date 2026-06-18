package ke.don.ma3routes.datasources.controller.repository

import ke.don.ma3routes.core.domain.repository.AuthRepository
import ke.don.ma3routes.core.domain.session.SessionManager
import ke.don.ma3routes.core.domain.util.isSuccess
import ke.don.ma3routes.datasources.remote.api.Ma3ApiService
import ke.don.ma3routes.datasources.remote.auth.GoogleSigninClient
import ke.don.ma3routes.datasources.remote.model.GoogleTokenRequest
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject constructor(
        private val googleSigninClient: GoogleSigninClient,
        private val apiService: Ma3ApiService,
        private val sessionManager: SessionManager,
    ): AuthRepository {
    override suspend fun signInWithGoogle(idToken: String): Result<Unit> {
        val googleResult = googleSigninClient.getCredentialIdToken()

        if (googleResult.isFailure) return Result.failure(googleResult.exceptionOrNull()!!)

        val googleIdToken = googleResult.getOrNull()!!

        val body = GoogleTokenRequest(idToken = googleIdToken)
        val result = apiService.signInWithGoogle(body = body)

        return if (result.isSuccess) {
            val session = result.data
            if (session != null) {
                sessionManager.saveSession(session.accessToken, session.refreshToken)
                Result.success(Unit)
            } else {
                Result.failure(Exception("Session data is null"))
            }
        } else {
            Result.failure(Exception(result.message))
        }
    }

    override suspend fun signOut(): Result<Unit> {
        TODO("Not yet implemented")
    }
}
