package com.jokku.weather.weather

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jokku.weather.R
import com.jokku.weather.databinding.FragmentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private lateinit var binding: FragmentWeatherBinding

    private lateinit var citySpinner: AppCompatSpinner
    private lateinit var seasonSpinner: AppCompatSpinner
    private lateinit var citySize: AppCompatTextView
    private lateinit var tempFormatSpinner: AppCompatSpinner
    private lateinit var averageTemp: AppCompatTextView

    private lateinit var sourceAverageTemp: String

    private val viewModel: WeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWeatherBinding.bind(view)
        citySpinner = binding.citySpinner
        seasonSpinner = binding.seasonSpinner
        citySize = binding.citySizeText
        tempFormatSpinner = binding.tempFormatSpinner
        averageTemp = binding.averageTempText

        val cityAdapter =
            ArrayAdapter(requireContext(), R.layout.list_item, mutableListOf<String>())
        citySpinner.adapter = cityAdapter

        val seasons = resources.getStringArray(R.array.season_array)
        val seasonAdapter = ArrayAdapter(requireContext(), R.layout.list_item, seasons)
        seasonSpinner.adapter = seasonAdapter

        val formats = resources.getStringArray(R.array.temp_format)
        val formatAdapter = ArrayAdapter(requireContext(), R.layout.list_item, formats)
        tempFormatSpinner.adapter = formatAdapter

        observeCities(cityAdapter)
        chooseCity(cityAdapter)
        chooseSeason(seasonAdapter)
        chooseTempFormat(formatAdapter)
        setupFab()
    }

    private fun observeCities(cityAdapter: ArrayAdapter<String>) {
        viewModel.getCitiesNames().observe(viewLifecycleOwner) {
            cityAdapter.addAll(it)
            cityAdapter.notifyDataSetChanged()
        }
    }


    private fun chooseCity(cityAdapter: ArrayAdapter<String>) {
        citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                cityAdapter.getItem(position)?.let { city ->
                    viewModel.getCitySizeByName(city).observe(viewLifecycleOwner) {
                        citySize.text = it
                    }
                    seasonSpinner.selectedItem?.toString().let { season ->
                        if (season != null) {
                            viewModel.getAverageSeasonTemp(city, season)
                                .observe(viewLifecycleOwner) {
                                    sourceAverageTemp = it
                                    viewModel.changeTempFormat(
                                        sourceAverageTemp,
                                        tempFormatSpinner.selectedItem.toString()
                                    ).observe(viewLifecycleOwner) { typedTemp ->
                                        averageTemp.text = typedTemp
                                        callSnackbar("Average temperature $typedTemp")
                                    }
                                }
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun chooseSeason(seasonAdapter: ArrayAdapter<String>) {
        seasonSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                citySpinner.selectedItem?.toString().let { city ->
                    seasonAdapter.getItem(position)?.let { season ->
                        if (city != null) {
                            viewModel.getAverageSeasonTemp(city, season)
                                .observe(viewLifecycleOwner) {
                                    sourceAverageTemp = it
                                    viewModel.changeTempFormat(
                                        sourceAverageTemp,
                                        tempFormatSpinner.selectedItem.toString()
                                    ).observe(viewLifecycleOwner) { typedTemp ->
                                        averageTemp.text = typedTemp
                                        callSnackbar("Average temperature $typedTemp")
                                    }
                                }
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun chooseTempFormat(formatAdapter: ArrayAdapter<String>) {
        tempFormatSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (this@WeatherFragment::sourceAverageTemp.isInitialized) {
                    viewModel.changeTempFormat(
                        sourceAverageTemp,
                        formatAdapter.getItem(position).toString()
                    ).observe(viewLifecycleOwner) {
                        averageTemp.text = it
                        callSnackbar("Average temperature $it")
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    private fun setupFab() {
        binding.addCityFab.let {
            it.setOnClickListener {
                val action = WeatherFragmentDirections.actionWeatherFragmentToPreferencesFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun callSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }
}