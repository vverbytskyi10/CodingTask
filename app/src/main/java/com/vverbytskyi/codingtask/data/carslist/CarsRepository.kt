package com.vverbytskyi.codingtask.data.carslist

import com.vverbytskyi.codingtask.data.common.NetworkResponse
import com.vverbytskyi.codingtask.data.network.model.Car

interface CarsRepository {

    suspend fun getCars(): NetworkResponse<List<Car>>
}