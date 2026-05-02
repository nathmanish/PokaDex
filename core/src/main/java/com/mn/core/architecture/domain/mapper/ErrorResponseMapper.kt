package com.mn.core.architecture.domain.mapper

import com.mn.core.architecture.data.models.ErrorResponse
import com.mn.core.architecture.domain.models.FailureResult

class ErrorResponseMapper : Mapper<ErrorResponse, FailureResult> {

    override fun invoke(from: ErrorResponse): FailureResult = when (from) {
        ErrorResponse.NoDataFound -> FailureResult.NoDataFound
        ErrorResponse.UnknownError -> FailureResult.UnknownError
        ErrorResponse.NoInternet -> FailureResult.NoInternet
        is ErrorResponse.ServerError -> {
            val (code, error) = from
            FailureResult.ServerError(code, error)
        }
    }

}