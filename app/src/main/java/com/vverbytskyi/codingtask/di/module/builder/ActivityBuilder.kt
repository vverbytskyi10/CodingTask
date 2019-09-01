package com.vverbytskyi.codingtask.di.module.builder

import com.vverbytskyi.codingtask.di.module.carslist.CarsListModule
import com.vverbytskyi.codingtask.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {

    @ContributesAndroidInjector(modules = [CarsListModule::class])
    fun contributeMainActivity(): MainActivity
}