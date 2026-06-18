package ke.don.ma3routes.datasources.controller.repository

import ke.don.ma3routes.core.domain.repository.AuthRepository
import ke.don.ma3routes.core.domain.session.SessionManager
import ke.don.ma3routes.datasources.controller.mapper.asEntity
import ke.don.ma3routes.datasources.local.dao.UserDao
import ke.don.ma3routes.datasources.remote.api.Ma3ApiService
import ke.don.ma3routes.datasources.remote.model.GoogleTokenRequest
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject constructor(
        private val apiService: Ma3ApiService,
        private val sessionManager: SessionManager,
        private val userDao: UserDao,
    ): AuthRepository {
    override suspend fun signInWithGoogle(idToken: String): Result<Unit> {
        return runCatching {
            val body = GoogleTokenRequest(idToken = idToken)
            val session = apiService.signInWithGoogle(body = body)
            sessionManager.saveSession(session.accessToken, session.refreshToken)
            userDao.upsert(session.user.asEntity())
        }
    }

    override suspend fun signOut(): Result<Unit> {
        sessionManager.clearSession()
        userDao.clearUsers()
        return Result.success(Unit)
    }
}
