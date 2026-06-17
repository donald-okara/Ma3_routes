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

/**
 * Types of interceptors that can be applied to a network call.
 */
enum class InterceptorType {
    /**
     * Adds the API key to the request headers.
     */
    API_KEY,

    /**
     * Adds the "Content-Type: application/json" header.
     */
    JSON_CONTENT_TYPE,

    /**
     * Enables full body logging for this specific call, even if global logging is less verbose.
     */
    VERBOSE_LOGGING
}

/**
 * Annotation to specify which interceptors should be applied to a Retrofit call.
 * If not specified, default interceptors may still be applied depending on implementation.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplyInterceptors(vararg val types: InterceptorType)
