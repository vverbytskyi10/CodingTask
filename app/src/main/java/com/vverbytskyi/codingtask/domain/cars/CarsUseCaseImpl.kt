package com.vverbytskyi.codingtask.domain.cars

import com.vverbytskyi.codingtask.data.cars.CarsRepository
import com.vverbytskyi.codingtask.data.common.FailureResponse
import com.vverbytskyi.codingtask.data.common.SuccessResponse
import com.vverbytskyi.codingtask.domain.cars.model.CarMapper
import com.vverbytskyi.codingtask.ui.common.CompletedState
import com.vverbytskyi.codingtask.ui.common.ErrorState
import com.vverbytskyi.codingtask.ui.common.NetworkState

class CarsUseCaseImpl(
    private val carMapper: CarMapper,
    private val carsRepository: CarsRepository): CarsUseCase {

    override suspend fun getCarsList(): NetworkState {
        return try {
            return when(val response = carsRepository.getCars()) {
                is SuccessResponse -> CompletedState(carMapper.dataToDomain(response.data))
                is FailureResponse -> { ErrorState(response.error.message) }
            }
        } catch (e: Throwable) {
            ErrorState(e.message)
        }
    }
}