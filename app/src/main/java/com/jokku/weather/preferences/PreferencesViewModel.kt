package com.jokku.weather.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokku.weather.data.local.entity.Weather
import com.jokku.weather.data.repo.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    fun insertWeather(weather: Weather) = viewModelScope.launch {
        repository.insertWeather(weather)
    }

}