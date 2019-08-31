package com.vverbytskyi.codingtask.di.module.builder

import com.vverbytskyi.codingtask.ui.cars.CarsListFragment
import com.vverbytskyi.codingtask.ui.cars.CarsMapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {

    @ContributesAndroidInjector
    fun contributeCarsListFragment(): CarsListFragment

    @ContributesAndroidInjector
    fun contributeCarsMapFragment(): CarsMapFragment
}