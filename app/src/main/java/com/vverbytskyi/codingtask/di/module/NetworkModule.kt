package com.vverbytskyi.codingtask.di.module

import com.vverbytskyi.codingtask.data.network.CarsService
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
class NetworkModule {

    @Provides
    fun provideApiHttpUrl(): HttpUrl {
        return HttpUrl.get("123")
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, apiHttpUrl: HttpUrl): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(apiHttpUrl)
            .build()
    }

    @Provides
    fun provideCarsService(retrofit: Retrofit): CarsService {
        return retrofit.create(CarsService::class.java)
    }
}