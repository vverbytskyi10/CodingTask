package com.vverbytskyi.codingtask.data.common

sealed class NetworkResponse<out T : Any>

data class SuccessResponse<out T : Any>(val data: T) : NetworkResponse<T>()

data class FailureResponse(val error: Throwable): NetworkResponse<Nothing>()