package com.elize.countries.ui.adapter

import com.elize.countries.fixtures.countryList
import junit.framework.Assert.assertEquals
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

        assertEquals(countryList.size, adapter.itemCount)
    }

    @Test
    fun `should update countries`() {
        adapter.updateCountries(countryList)

        assertEquals(countryList, adapter.countries)
    }

    @Test
    fun `should clear items`() {
        adapter.clearAllItems()

        assertEquals(0, adapter.itemCount)
    }

}