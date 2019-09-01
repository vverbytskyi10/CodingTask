package com.vverbytskyi.codingtask.di.module.mappers

import com.vverbytskyi.codingtask.domain.cars.model.CarMapper
import dagger.Module
import dagger.Provides

@Module
class MappersModule {

    @Provides
    fun provideCarMapper(): CarMapper = CarMapper()
}