package com.elize.countries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.elize.countries.databinding.ItemCountryBinding
import com.elize.countries.extension.loadImage
import com.elize.countries.model.Country
import com.elize.countries.util.getProgressDrawable

class CountryListAdapter(var countries: ArrayList<Country>) :
    Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemBinding =
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount() = countries.size


    class CountryViewHolder(itemBinding: ItemCountryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val countryName = itemBinding.itemCountryName
        private val imageView = itemBinding.itemCountryImage
        private val capital = itemBinding.itemCountryCapital
        private val progressDrawable = getProgressDrawable(itemBinding.root.context)

        fun bind(country: Country) {
            countryName.text = country.countryName
            capital.text = country.capital
            imageView.loadImage(country.flag, progressDrawable)
        }

    }

}

