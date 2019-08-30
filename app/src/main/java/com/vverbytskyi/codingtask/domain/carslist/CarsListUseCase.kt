package com.vverbytskyi.codingtask.domain.carslist

import com.vverbytskyi.codingtask.ui.common.NetworkState

interface CarsListUseCase {

    suspend fun getCarsList(): NetworkState
}