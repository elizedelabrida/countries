package com.elize.countries.countrylist

import com.elize.countries.BaseRobot
import com.elize.countries.R

fun countryList(func: CountryListRobot.() -> Unit) = CountryListRobot()
    .apply { func() }

class CountryListRobot : BaseRobot() {
    fun checkRecyclerIsVisible() =
        checkViewIsDisplayed(R.id.country_list_recyclerView)

    fun scrollToPosition(position: Int) =
        scrollToPosition(R.id.country_list_recyclerView, position)

    fun findItemAtRecycler(withText: String) =
        findItemInRecyclerView(R.id.country_list_recyclerView, withText)

    fun checkErrorIsVisible() =
        checkViewIsDisplayed(R.id.country_list_error)

    fun waitCountriesApi() = sleep(500)
}