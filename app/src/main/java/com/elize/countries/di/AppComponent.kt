package com.elize.countries.di

import com.elize.countries.retrofit.CountryRepository
import com.elize.countries.ui.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(countryRepository: CountryRepository)
    fun inject(listViewModel: ListViewModel)
}