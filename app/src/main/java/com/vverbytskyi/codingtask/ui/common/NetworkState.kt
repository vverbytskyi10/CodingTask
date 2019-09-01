package com.vverbytskyi.codingtask.ui.common

sealed class NetworkState

object StartedState : NetworkState()

data class CompletedState<out T : Any>(val data: T) : NetworkState()

data class ErrorState(val message: String?) : NetworkState()