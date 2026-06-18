/*
 * Copyright 2025 Donald Isoe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ke.don.ma3routes.datasources.remote.api

import ke.don.ma3routes.core.domain.session.SessionManager
import ke.don.ma3routes.core.domain.util.isSuccess
import ke.don.ma3routes.datasources.remote.BuildConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import javax.inject.Inject
import javax.inject.Provider

class DslInterceptor @Inject constructor(
    private val sessionManager: SessionManager,
    private val apiServiceProvider: Provider<Ma3ApiService>
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val invocation = request.tag(Invocation::class.java)
        val annotation = invocation?.method()?.getAnnotation(ApplyInterceptors::class.java)

        val types = annotation?.types ?: arrayOf(InterceptorType.API_KEY, InterceptorType.JSON_CONTENT_TYPE)

        val builder = request.newBuilder()

        if (InterceptorType.API_KEY in types) {
            builder.addHeader("apikey", BuildConfig.API_KEY)
        }

        if (InterceptorType.JSON_CONTENT_TYPE in types) {
            builder.addHeader("Content-Type", "application/json")
        }

        val initialToken = runBlocking { sessionManager.getAccessToken().first() }
        if (InterceptorType.AUTH in types && initialToken != null) {
            builder.addHeader("Authorization", "Bearer $initialToken")
        }

        val initialResponse = chain.proceed(builder.build())

        if (initialResponse.code == 401 && InterceptorType.AUTH in types) {
            initialResponse.close()

            val synchronizedResult = synchronized(this) {
                runBlocking {
                    val currentToken = sessionManager.getAccessToken().first()
                    if (currentToken != initialToken) {
                        return@runBlocking currentToken
                    }

                    // Try to refresh
                    val refreshToken = sessionManager.getRefreshToken().first()
                    if (refreshToken != null) {
                        runCatching {
                            val session = apiServiceProvider.get().refreshToken(refreshToken = refreshToken)
                            sessionManager.saveSession(session.accessToken, session.refreshToken)
                            session.accessToken
                        }.getOrNull() ?: run {
                            sessionManager.clearSession()
                            null
                        }
                    } else {
                        null
                    }
                }
            }

            if (synchronizedResult != null) {
                val newRequest = request.newBuilder()
                    .header("Authorization", "Bearer $synchronizedResult")
                    .build()
                return chain.proceed(newRequest)
            }
        }

        return initialResponse
    }
}
