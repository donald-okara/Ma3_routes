package ke.don.ma3routes.core.domain.util

/**
 * ApiResponse is a generic class that represents the response from the API.
 */
data class ApiResponse<T>(
    val status: Int,
    val message: String,
    val data: T? = null
)

val ApiResponse<*>.isSuccess: Boolean
    get() = status in 200..299

val ApiResponse<*>.isFailure: Boolean
    get() = isSuccess.not()
