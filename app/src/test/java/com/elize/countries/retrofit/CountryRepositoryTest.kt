package com.elize.countries.retrofit

import com.elize.countries.retrofit.service.CountryService
import com.elize.countries.utils.countrySingleList
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CountryRepositoryTest {
    @Mock
    lateinit var countryService: CountryService

    @InjectMocks
    var countryRepository = CountryRepository()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `when service returns countries, repository should return the same data`() {
        Mockito.`when`(countryService.getCountries()).thenReturn(countrySingleList)

        val countriesResult = countryRepository.getCountries()

        assertEquals(countrySingleList, countriesResult)
    }
}