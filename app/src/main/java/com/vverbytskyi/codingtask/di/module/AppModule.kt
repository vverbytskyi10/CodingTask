package com.vverbytskyi.codingtask.di.module

import android.content.Context
import com.vverbytskyi.codingtask.App
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: App): Context {
        return application.applicationContext
    }
}