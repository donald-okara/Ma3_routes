package ke.don.ma3routes.core.domain.util

sealed interface ResultStatus {
    object Idle: ResultStatus
    object Loading: ResultStatus
    object Success: ResultStatus
    data class Error(val message: String): ResultStatus
}

val ResultStatus.isLoading
    get() = this is ResultStatus.Loading

val ResultStatus.isSuccess
    get() = this is ResultStatus.Success

val ResultStatus.isError
    get() = this is ResultStatus.Error

val ResultStatus.isIdle
    get() = this is ResultStatus.Idle

val ResultStatus.message : String?
    get() = (this as? ResultStatus.Error)?.message
