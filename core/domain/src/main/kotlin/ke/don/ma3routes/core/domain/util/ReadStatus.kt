package ke.don.ma3routes.core.domain.util

sealed interface ReadStatus {
    object Empty: ReadStatus
    object Loading: ReadStatus
    object Success: ReadStatus
    data class Error(val message: String): ReadStatus
}

val ReadStatus.isLoading
    get() = this is ReadStatus.Loading

val ReadStatus.isSuccess
    get() = this is ReadStatus.Success

val ReadStatus.isError
    get() = this is ReadStatus.Error

val ReadStatus.isIdle
    get() = this is ReadStatus.Empty

val ReadStatus.message : String?
    get() = (this as? ReadStatus.Error)?.message
