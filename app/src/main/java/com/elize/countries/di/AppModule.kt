package com.elize.countries.di

import com.elize.countries.retrofit.CountryRepository
import com.elize.countries.retrofit.service.CountryService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {
    //Use Hilt: https://developer.android.com/training/dependency-injection/hilt-android

    private val BASE_URL = "https://raw.githubusercontent.com"

    @Provides
    fun provideCountryService() : CountryService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CountryService::class.java)
    }
    @Provides
    fun provideCountryRepository() : CountryRepository {
        return CountryRepository()
    }

}