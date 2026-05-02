package com.mn.core.architecture.domain.models

sealed interface FailureResult {
    data class ServerError(val code: String?, val error: String?) : FailureResult

    data object NoDataFound : FailureResult

    data object UnknownError : FailureResult

    data object NoInternet : FailureResult
}