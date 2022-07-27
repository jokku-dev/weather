package com.jokku.weather.preferences

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
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

    private lateinit var city: EditText
    private lateinit var citySize: Spinner

    private lateinit var december: EditText
    private lateinit var january: EditText
    private lateinit var february: EditText
    private lateinit var march: EditText
    private lateinit var april: EditText
    private lateinit var may: EditText
    private lateinit var june: EditText
    private lateinit var july: EditText
    private lateinit var august: EditText
    private lateinit var september: EditText
    private lateinit var october: EditText
    private lateinit var november: EditText

    private val viewModel: PreferencesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPreferencesBinding.bind(view)
        city = binding.cityNameText
        citySize = binding.citySizeSpinner
        december = binding.monthTemp1
        january = binding.monthTemp2
        february = binding.monthTemp3
        march = binding.monthTemp4
        april = binding.monthTemp5
        may = binding.monthTemp6
        june = binding.monthTemp7
        july = binding.monthTemp8
        august = binding.monthTemp9
        september = binding.monthTemp10
        october = binding.monthTemp11
        november = binding.monthTemp12
        setupFab()
    }

    private fun setupFab() {
        binding.confirmFab.let { fab ->
            fab.setOnClickListener { click ->
                val city = city.text.toString()
                val size = citySize.selectedItem.toString()
                val winterTemps = listOf(december.text.toString(), january.text.toString(),
                    february.text.toString())
                val springTemps = listOf(march.text.toString(), april.text.toString(),
                    may.text.toString())
                val summerTemps = listOf(june.text.toString(), july.text.toString(),
                    august.text.toString())
                val autumnTemps = listOf(september.text.toString(), october.text.toString(),
                    november.text.toString())

                if (city.isNotEmpty() && size.isNotEmpty()
                    && december.text.isNotEmpty() && january.text.isNotEmpty() && february.text.isNotEmpty()
                    && march.text.isNotEmpty() && april.text.isNotEmpty() && may.text.isNotEmpty()
                    && june.text.isNotEmpty() && july.text.isNotEmpty() && august.text.isNotEmpty()
                    && september.text.isNotEmpty() && october.text.isNotEmpty() && november.text.isNotEmpty()) {
                    val weather = Weather(city, size, winterTemps, springTemps, summerTemps, autumnTemps)
                    viewModel.insertWeather(weather)
                    val action =
                        PreferencesFragmentDirections.actionPreferencesFragmentToWeatherFragment()
                    findNavController().navigate(action)
                } else {
                    Snackbar.make(click, "You need to fill all the fields", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}