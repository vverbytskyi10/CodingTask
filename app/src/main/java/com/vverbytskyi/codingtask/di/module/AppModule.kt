package com.vverbytskyi.codingtask.di.module

import android.content.Context
import com.vverbytskyi.codingtask.App
import dagger.Module

@Module
interface AppModule {

    fun provideContext(application: App): Context {
        return application.applicationContext
    }
}