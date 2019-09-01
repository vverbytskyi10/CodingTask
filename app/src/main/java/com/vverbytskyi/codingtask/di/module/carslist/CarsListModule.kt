package com.vverbytskyi.codingtask.di.module.carslist

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.vverbytskyi.codingtask.data.carslist.CarsRepository
import com.vverbytskyi.codingtask.data.carslist.CarsRepositoryImpl
import com.vverbytskyi.codingtask.data.network.CarsService
import com.vverbytskyi.codingtask.domain.cars.CarsUseCase
import com.vverbytskyi.codingtask.domain.cars.CarsUseCaseImpl
import com.vverbytskyi.codingtask.domain.cars.model.CarMapper
import com.vverbytskyi.codingtask.ui.MainViewModel
import com.vverbytskyi.codingtask.ui.cars.list.CarsListAdapter
import com.vverbytskyi.codingtask.ui.common.ViewModelProviderFactory
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
    @Provides
    fun provideCarsListUseCase(
        carMapper: CarMapper,
        carsRepository: CarsRepository
    ): CarsUseCase = CarsUseCaseImpl(carMapper, carsRepository)
    // endregion

    // region: UI
    @Provides
    fun provideCarsListAdapter(context: Context): CarsListAdapter {
        return CarsListAdapter(context)
    }

    @Provides
    fun provideViewModel(carsUseCase: CarsUseCase): MainViewModel {
        return MainViewModel(carsUseCase)
    }

    @Provides
    fun provideViewModelProvider(mainViewModel: MainViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(mainViewModel)
    }
    // endregion
}