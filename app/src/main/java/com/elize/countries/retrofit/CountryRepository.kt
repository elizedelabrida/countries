package com.elize.countries.retrofit

import com.elize.countries.di.DaggerAppComponent
import com.elize.countries.model.Country
import com.elize.countries.retrofit.service.CountryService
import io.reactivex.Single
import javax.inject.Inject

class CountryRepository {

    @Inject
    lateinit var service: CountryService

    init {
        DaggerAppComponent.create().inject(this)
    }

    fun getCountries() : Single<List<Country>> {
        return service.getCountries()
    }


}