package com.vverbytskyi.codingtask.di.module.builder

import com.vverbytskyi.codingtask.ui.carslist.CarsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {

    @ContributesAndroidInjector
    fun contributeCarsListFragment(): CarsListFragment
}