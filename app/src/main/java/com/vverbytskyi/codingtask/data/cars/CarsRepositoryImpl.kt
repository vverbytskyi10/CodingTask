package com.vverbytskyi.codingtask.data.cars

import com.vverbytskyi.codingtask.data.common.FailureResponse
import com.vverbytskyi.codingtask.data.common.NetworkResponse
import com.vverbytskyi.codingtask.data.common.SuccessResponse
import com.vverbytskyi.codingtask.data.network.CarsService
import com.vverbytskyi.codingtask.data.network.model.Car

class CarsRepositoryImpl(private val carsService: CarsService) : CarsRepository {

    override suspend fun getCars(): NetworkResponse<List<Car>> {
        return try {
            val response = carsService.getCarsList()
            val responseData = response.body()

            when {
                responseData != null -> SuccessResponse(responseData)
                else -> FailureResponse(Throwable(response.message()))
            }
        } catch (e: Throwable) {
            FailureResponse(e)
        }
    }
}