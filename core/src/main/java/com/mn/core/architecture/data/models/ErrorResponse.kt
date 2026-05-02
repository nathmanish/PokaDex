package com.mn.core.architecture.data.models

sealed interface ErrorResponse {
    data class ServerError(val code: String?, val message: String?) : ErrorResponse
    data object NoDataFound : ErrorResponse
    data object UnknownError : ErrorResponse
    data object NoInternet : ErrorResponse
}