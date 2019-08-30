package com.vverbytskyi.codingtask.di.component

import com.vverbytskyi.codingtask.App
import com.vverbytskyi.codingtask.di.module.AppModule
import com.vverbytskyi.codingtask.di.module.NetworkModule
import com.vverbytskyi.codingtask.di.module.builder.ActivityBuilder
import com.vverbytskyi.codingtask.di.module.builder.FragmentBuilder
import com.vverbytskyi.codingtask.di.module.mappers.MappersModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MappersModule::class,
        NetworkModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App>
}