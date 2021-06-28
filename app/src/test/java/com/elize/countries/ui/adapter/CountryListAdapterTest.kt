package com.elize.countries.ui.adapter

import com.elize.countries.model.Country
import com.elize.countries.utils.countryList
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE)
class CountryListAdapterTest {

    private val adapter : CountryListAdapter = CountryListAdapter(arrayListOf())

    @Test
    fun `should return correct size`() {
        adapter.updateCountries(countryList)

        Assert.assertEquals(countryList.size, adapter.itemCount)
    }

    @Test
    fun `should update countries`() {
        adapter.updateCountries(countryList)

        Assert.assertEquals(countryList, adapter.countries)
    }

    @Test
    fun `should clear items`() {
        adapter.clearAllItems()

        Assert.assertEquals(0, adapter.itemCount)
    }

}