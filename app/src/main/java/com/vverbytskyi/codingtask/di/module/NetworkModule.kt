package com.vverbytskyi.codingtask.di.module

import com.vverbytskyi.codingtask.BuildConfig
import com.vverbytskyi.codingtask.data.network.CarsService
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideApiHttpUrl(): HttpUrl = HttpUrl.get(BuildConfig.API_URL)

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    fun provideConverterFactory(): Converter.Factory = MoshiConverterFactory.create()

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        apiHttpUrl: HttpUrl,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .baseUrl(apiHttpUrl)
        .build()

    @Provides
    fun provideCarsService(retrofit: Retrofit): CarsService =
        retrofit.create(CarsService::class.java)
}