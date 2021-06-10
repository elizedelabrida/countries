package com.elize.countries.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elize.countries.di.DaggerAppComponent
import com.elize.countries.model.Country
import com.elize.countries.retrofit.CountryRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel : ViewModel() {
    private val disposable = CompositeDisposable()
    val countries = MutableLiveData<List<Country>>() // To study MVVM: Variable to watch

    // current countries
    val countryLoadError = MutableLiveData<Boolean>() // To study MVVM: Variable to check if there
    // was an error after loading
    val loading = MutableLiveData<Boolean>() // To study MVVM: Variable to check if date
    // is being loaded from back-end

    @Inject
    lateinit var countryRepository: CountryRepository

    init {
        DaggerAppComponent.create().inject(this)
    }

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        setDisposable()
    }

    private fun setDisposable() {
        disposable.add(
            countryRepository.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(
                    getCountriesObserver()
                )
        )
    }

    private fun getCountriesObserver() = object : DisposableSingleObserver<List<Country>>() {
        override fun onSuccess(value: List<Country>?) {
            updateCountriesStatus(value)
        }

        override fun onError(e: Throwable?) {
            updateErrorStatus()
        }

    }

    private fun updateErrorStatus() {
        countryLoadError.value = true
        loading.value = false
    }

    private fun updateCountriesStatus(value: List<Country>?) {
        countries.value = value
        countryLoadError.value = false
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}