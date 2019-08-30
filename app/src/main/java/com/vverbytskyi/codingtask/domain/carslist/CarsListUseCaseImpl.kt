package com.vverbytskyi.codingtask.domain.carslist

import com.vverbytskyi.codingtask.data.carslist.CarsRepository
import com.vverbytskyi.codingtask.data.common.FailureResponse
import com.vverbytskyi.codingtask.data.common.SuccessResponse
import com.vverbytskyi.codingtask.domain.carslist.model.CarMapper
import com.vverbytskyi.codingtask.ui.common.CompletedState
import com.vverbytskyi.codingtask.ui.common.ErrorState
import com.vverbytskyi.codingtask.ui.common.NetworkState

class CarsListUseCaseImpl(
    private val carMapper: CarMapper,
    private val carsRepository: CarsRepository): CarsListUseCase {

    override suspend fun getCarsList(): NetworkState {
        return try {
            return when(val response = carsRepository.getCars()) {
                is SuccessResponse -> CompletedState(carMapper.dataToDomain(response.data))
                is FailureResponse -> {
                    ErrorState("")
                }
            }
        } catch (e: Throwable) {
            ErrorState(e.message)
        }
    }
}