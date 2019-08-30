package com.vverbytskyi.codingtask.data.network

import com.vverbytskyi.codingtask.data.network.model.Car
import retrofit2.Response

interface CarsService {

    suspend fun getCarsList(): Response<List<Car>>
}