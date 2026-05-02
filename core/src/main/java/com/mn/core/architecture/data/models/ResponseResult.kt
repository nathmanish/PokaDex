package com.mn.core.architecture.data.models

sealed class ResponseResult<out R> {
    data class Success<R>(val data: R) : ResponseResult<R>()
    data class Failure<R>(val error: ErrorResponse) : ResponseResult<R>()
}