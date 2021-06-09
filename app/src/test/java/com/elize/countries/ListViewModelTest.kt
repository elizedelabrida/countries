package com.elize.countries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.elize.countries.model.Country
import com.elize.countries.retrofit.CountryRepository
import com.elize.countries.ui.viewmodel.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit


class ListViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var countryRepository: CountryRepository

    @InjectMocks
    var listViewModel = ListViewModel()

    private var testSingle: Single<List<Country>>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getCountriesSuccess() {
        val country = Country("countryName", "capital", "url")
        val countriesList = arrayListOf(country)

        testSingle = Single.just(countriesList)
        Mockito.`when`(countryRepository.getCountries()).thenReturn(testSingle)
        listViewModel.refresh()

        Assert.assertEquals(1, listViewModel.countries.value?.size)
        Assert.assertEquals(false, listViewModel.countryLoadError.value)
        Assert.assertEquals(false, listViewModel.loading.value)
    }

    @Test
    fun getCountriesFail() {
        testSingle = Single.error(Throwable())
        Mockito.`when`(countryRepository.getCountries()).thenReturn(testSingle)
        listViewModel.refresh()

        Assert.assertEquals(true, listViewModel.countryLoadError.value)
        Assert.assertEquals(false, listViewModel.loading.value)
    }

    @Before
    fun setUpRxSchedulers() {
        val immediateScheduler = setImmediateScheduler()
        setRxPlugins(immediateScheduler)
    }

    private fun setImmediateScheduler() = object : Scheduler() {
        override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
            return super.scheduleDirect(run, 0, unit)
        }

        override fun createWorker(): Worker {
            return ExecutorScheduler.ExecutorWorker { it.run() }
        }
    }

    private fun setRxPlugins(scheduler: Scheduler) {
        RxJavaPlugins.setInitIoSchedulerHandler { scheduler }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
    }

}