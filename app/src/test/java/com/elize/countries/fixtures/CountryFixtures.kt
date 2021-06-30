package com.elize.countries.fixtures

import com.elize.countries.model.Country
import io.reactivex.Single

val countryList = arrayListOf(
    Country("Test", "Capital test", "flagTest.png")
)

val countrySingleList: Single<List<Country>> =
    Single.just(
        countryList
    )

val countrySingleListError: Single<List<Country>> =
    Single.error(Throwable())

