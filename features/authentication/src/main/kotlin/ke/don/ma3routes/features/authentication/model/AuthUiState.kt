package ke.don.ma3routes.features.authentication.model

import ke.don.ma3routes.core.domain.util.ResultStatus

data class AuthUiState(
    val status: ResultStatus = ResultStatus.Idle,
)
