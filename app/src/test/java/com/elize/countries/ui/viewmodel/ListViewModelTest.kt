package com.elize.countries.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.elize.countries.fixtures.countrySingleList
import com.elize.countries.fixtures.countrySingleListError
import com.elize.countries.retrofit.CountryRepository
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import junit.framework.Assert.assertEquals
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

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
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


    @Test
    fun `when repository returns valid country, viewModel state should be success`() {
        Mockito.`when`(countryRepository.getCountries()).thenReturn(countrySingleList)

        listViewModel.refresh()

        assertEquals(1, listViewModel.countries.value?.size)
        assertEquals(false, listViewModel.countryLoadError.value)
        assertEquals(false, listViewModel.loading.value)
    }

    @Test
    fun `when repository returns error, viewModel state should be error`() {
        Mockito.`when`(countryRepository.getCountries()).thenReturn(countrySingleListError)

        listViewModel.refresh()

        assertEquals(true, listViewModel.countryLoadError.value)
        assertEquals(false, listViewModel.loading.value)
    }


}