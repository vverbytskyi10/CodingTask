package com.vverbytskyi.codingtask.di.module.carslist

import com.vverbytskyi.codingtask.data.carslist.CarsRepository
import com.vverbytskyi.codingtask.data.carslist.CarsRepositoryImpl
import com.vverbytskyi.codingtask.data.network.CarsService
import com.vverbytskyi.codingtask.domain.carslist.CarsListUseCase
import com.vverbytskyi.codingtask.domain.carslist.CarsListUseCaseImpl
import com.vverbytskyi.codingtask.domain.carslist.model.CarMapper
import dagger.Module
import dagger.Provides

@Module
class CarsListModule {

    // region: data
    @Provides
    fun provideCarsRepository(carsService: CarsService): CarsRepository =
        CarsRepositoryImpl(carsService)
    // endregion

    // region: domain
    fun provideCarsListUseCase(
        carMapper: CarMapper,
        carsRepository: CarsRepository
    ): CarsListUseCase = CarsListUseCaseImpl(carMapper, carsRepository)
    // endregion
}