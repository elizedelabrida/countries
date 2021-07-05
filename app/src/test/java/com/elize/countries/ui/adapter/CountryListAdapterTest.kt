package com.elize.countries.ui.adapter

import com.elize.countries.fixtures.countryList
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class CountryListAdapterTest {

    @Spy
    lateinit var adapter: CountryListAdapter

    @Before
    fun setup() {
        adapter = CountryListAdapter(countryList)
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `should return correct size`() {
        adapter.updateCountries(countryList)

        assertThat(adapter.itemCount, equalTo(countryList.size))
    }

    @Test
    fun `should update countries`() {
        adapter.updateCountries(countryList)

        verify(adapter).notifyDataSetChanged()
        assertThat(countryList, equalTo(adapter.countries))
    }

    @Test
    fun `should clear items`() {
        adapter.clearAllItems()

        verify(adapter).notifyDataSetChanged()
        assertThat(0, equalTo(adapter.itemCount))
    }

}