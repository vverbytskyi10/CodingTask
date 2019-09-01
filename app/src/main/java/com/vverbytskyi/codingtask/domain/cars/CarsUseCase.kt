package com.vverbytskyi.codingtask.domain.cars

import com.vverbytskyi.codingtask.ui.common.NetworkState

interface CarsUseCase {

    suspend fun getCarsList(): NetworkState
}