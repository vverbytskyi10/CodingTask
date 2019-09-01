package com.vverbytskyi.codingtask.data.network

import com.vverbytskyi.codingtask.data.network.model.Car
import retrofit2.Response
import retrofit2.http.GET

interface CarsService {

    @GET("cars")
    suspend fun getCarsList(): Response<List<Car>>
}