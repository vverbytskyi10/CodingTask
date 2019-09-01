package com.vverbytskyi.codingtask.di.module.builder

import com.vverbytskyi.codingtask.di.module.carslist.CarsListModule
import com.vverbytskyi.codingtask.ui.cars.list.CarsListFragment
import com.vverbytskyi.codingtask.ui.cars.map.CarsMapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {

    @ContributesAndroidInjector(modules = [CarsListModule::class])
    fun contributeCarsListFragment(): CarsListFragment

    @ContributesAndroidInjector
    fun contributeCarsMapFragment(): CarsMapFragment
}