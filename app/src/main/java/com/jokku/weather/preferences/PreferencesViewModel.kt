package com.jokku.weather.preferences

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokku.weather.data.Weather
import com.jokku.weather.data.source.WeatherDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val weatherLocalDataSource: WeatherDataSource
) : ViewModel() {

    private val city = MutableLiveData<String>()
    private val size = MutableLiveData<String>()
    private val winterTemps = MutableLiveData<List<String>>()
    private val springTemps = MutableLiveData<List<String>>()
    private val summerTemps = MutableLiveData<List<String>>()
    private val autumnTemps = MutableLiveData<List<String>>()

    fun saveCityWeather() {
        val currentCity = city.value
        val currentCitySize = size.value
        val currentWinterTemps = winterTemps.value
        val currentSpringTemps = springTemps.value
        val currentSummerTemps = summerTemps.value
        val currentAutumnTemps = autumnTemps.value

        val weather = Weather(
            name = currentCity,
            size = currentCitySize,
            winterTemps = currentWinterTemps,
            springTemps = currentSpringTemps,
            summerTemps = currentSummerTemps,
            autumnTemps = currentAutumnTemps
        )
        createCityWeather(weather)
    }

    private fun createCityWeather(weather: Weather) = viewModelScope.launch {
        weatherLocalDataSource.insertWeather(weather)
    }
}