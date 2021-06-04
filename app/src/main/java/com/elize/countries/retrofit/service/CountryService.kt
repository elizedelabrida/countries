package com.elize.countries.retrofit.service

import com.elize.countries.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryService {
    @GET("DevTides/countries/master/countriesV2.json")
    fun getCountries(): Single<List<Country>>
}