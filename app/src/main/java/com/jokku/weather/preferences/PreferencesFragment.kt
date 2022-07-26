package com.jokku.weather.preferences

import android.os.Bundle
import android.view.View
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jokku.weather.R
import com.jokku.weather.data.local.entity.Weather
import com.jokku.weather.databinding.FragmentPreferencesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreferencesFragment : Fragment(R.layout.fragment_preferences) {

    private lateinit var binding: FragmentPreferencesBinding

    private val viewModel: PreferencesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPreferencesBinding.bind(view)
        setupFab()
    }

    private fun setupFab() {
        binding.confirmFab.let { fab ->
            fab.setOnClickListener { click ->
                val city = binding.prefCityNameEdit.text.toString()
                val size = binding.prefCitySizeSpinner.selectedItem.toString()
                val winterTemps = listOf(binding.monthTemp1.text.toString(),
                    binding.monthTemp2.text.toString(), binding.monthTemp3.text.toString())
                val springTemps = listOf(binding.monthTemp4.text.toString(),
                    binding.monthTemp5.text.toString(), binding.monthTemp6.text.toString())
                val summerTemps = listOf(binding.monthTemp7.text.toString(),
                    binding.monthTemp8.text.toString(), binding.monthTemp9.text.toString())
                val autumnTemps = listOf(binding.monthTemp10.text.toString(),
                    binding.monthTemp11.text.toString(), binding.monthTemp12.text.toString())
                val weather = Weather(city, size, winterTemps, springTemps, summerTemps, autumnTemps)
                if (city.isNotEmpty() && size.isNotEmpty() && winterTemps.isNotEmpty()
                    && springTemps.isNotEmpty() && summerTemps.isNotEmpty() && autumnTemps.isNotEmpty()) {
                    viewModel.insertWeather(weather)
                    val action =
                        PreferencesFragmentDirections.actionPreferencesFragmentToWeatherFragment()
                    findNavController().navigate(action)
                } else {
                    Snackbar.make(click, "Fill all the fields", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}