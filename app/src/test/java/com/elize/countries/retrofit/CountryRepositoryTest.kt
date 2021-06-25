package com.elize.countries.retrofit

import com.elize.countries.model.Country
import com.elize.countries.retrofit.service.CountryService
import io.reactivex.Single
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
        val testCountrySingleList =
            Single.just(listOf(
                Country("Test", "Capital test", "flagTest.png")))
        Mockito.`when`(countryService.getCountries()).thenReturn(testCountrySingleList)

        val countriesResult = countryRepository.getCountries()

        assertEquals(testCountrySingleList, countriesResult)
    }
}