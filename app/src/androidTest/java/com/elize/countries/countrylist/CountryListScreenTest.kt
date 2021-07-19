package com.elize.countries.countrylist

import androidx.test.core.app.ActivityScenario
import androidx.test.filters.LargeTest
import com.elize.countries.ui.view.CountryListActivity
import com.elize.countries.util.NetworkUtil
import org.junit.After
import org.junit.Before
import org.junit.Test


@LargeTest
class CountryListScreenTest {
    private lateinit var activityScenario : ActivityScenario<CountryListActivity>
    private val networkUtil = NetworkUtil()

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(CountryListActivity::class.java)
    }

    @Test
    fun whenLoadCountries_shouldDisplayCountryList() {
        countryList {
            checkRecyclerIsVisible()
        }
    }

    @Test
    fun whenLoadVariousCountries_shouldBeAbleToScroll() {
        val invisiblePositionOnRecyclerView = 9
        countryList {
            scrollToPosition(invisiblePositionOnRecyclerView)
        }
    }

    @Test
    fun whenLoadCountries_shouldDisplayCountryInformation() {
        val country = "Afghanistan"
        val capital = "Kabul"
        countryList {
            waitCountriesApi()
            findItemAtRecycler(country)
            findItemAtRecycler(capital)
        }
    }

    @Test
    fun whenCountriesNotLoad_shouldDisplayError() {
        networkUtil.toggleConnectionState(false)
        countryList {
            waitCountriesApi()
            checkErrorIsVisible()
        }
    }

    @After
    fun tearDown() {
        networkUtil.toggleConnectionState(true)
    }
}


