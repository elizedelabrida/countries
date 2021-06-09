package com.elize.countries.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elize.countries.R
import com.elize.countries.databinding.ActivityCountryListBinding
import com.elize.countries.extension.changeVisibility
import com.elize.countries.ui.adapter.CountryListAdapter
import com.elize.countries.ui.viewmodel.ListViewModel

class CountryListActivity : AppCompatActivity(R.layout.activity_country_list) {
    private lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())
    private val viewBinding by lazy {
        ActivityCountryListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        initializeViewModel()
        setListAdapter()
        observeViewModel()
        configureRefreshLayout()

    }

    private fun configureRefreshLayout() {
        with(viewBinding.countryListRefreshLayout) {
            setOnRefreshListener {
                isRefreshing = false
                viewModel.refresh()
            }
        }
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()
    }

    private fun setListAdapter() {
        viewBinding.countryListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
    }

    private fun observeViewModel() {
        observeCountries()
        observeCountryLoadError()
        observeLoading()
    }

    private fun observeLoading() {
        viewModel.loading.observe(this, Observer { displayLoading ->
            viewBinding.countryListLoading.changeVisibility(displayLoading)
        })
    }

    private fun observeCountryLoadError() {
        viewModel.countryLoadError.observe(this, Observer { displayError ->
            viewBinding.countryListError.changeVisibility(displayError)
        })
    }

    private fun observeCountries() {
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let { countriesAdapter.updateCountries(countries) }
        })
    }
}


