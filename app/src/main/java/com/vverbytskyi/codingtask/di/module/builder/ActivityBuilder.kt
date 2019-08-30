package com.vverbytskyi.codingtask.di.module.builder

import com.vverbytskyi.codingtask.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {

    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity
}