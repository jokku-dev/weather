package com.jokku.weather.weather

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jokku.weather.R
import com.jokku.weather.databinding.FragmentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private lateinit var binding: FragmentWeatherBinding

    private val viewModel: WeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWeatherBinding.bind(view)

        val citySpinner = binding.citySpinner
        val cityAdapter =
            ArrayAdapter(requireContext(), R.layout.list_item, mutableListOf<String>())
        cityAdapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        citySpinner.adapter = cityAdapter
        viewModel.getCitiesNames().observe(viewLifecycleOwner) {
            cityAdapter.addAll(it)
            cityAdapter.notifyDataSetChanged()
        }

        val citySizeText = binding.citySizeText
        val selectedCity: String = citySpinner.selectedItem.toString()
        viewModel.getCitySizeByName(selectedCity).observe(viewLifecycleOwner) {
            citySizeText.text = it
        }

        val seasonSpinner = binding.seasonSpinner
        val seasons = resources.getStringArray(R.array.season_array)
        val seasonAdapter = ArrayAdapter(requireContext(), R.layout.list_item, seasons)
        seasonAdapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        seasonSpinner.adapter = seasonAdapter


        val averageTemp = binding.averageTempText
        val selectedSeason = seasonSpinner.selectedItem.toString()
        viewModel.getAverageSeasonTemp(selectedCity, selectedSeason).observe(viewLifecycleOwner) {
            averageTemp.text = it
        }

        setupFab()
    }

    private fun setupFab() {
        binding.addCityFab.let {
            it.setOnClickListener {
                val action = WeatherFragmentDirections.actionWeatherFragmentToPreferencesFragment()
                findNavController().navigate(action)
            }
        }
    }
}