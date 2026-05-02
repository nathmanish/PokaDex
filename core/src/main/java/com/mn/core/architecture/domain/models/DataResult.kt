package com.mn.core.architecture.domain.models

sealed class DataResult<out D> {
    data class Success<D>(val data: D) : DataResult<D>()
    data class Failure<D>(val error : FailureResult) : DataResult<D>()
}