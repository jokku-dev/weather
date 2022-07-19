package com.jokku.weather.cityweather

import androidx.lifecycle.*
import com.jokku.weather.data.Weather
import com.jokku.weather.data.source.WeatherDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherLocalDataSource: WeatherDataSource
) : ViewModel() {

    private val _cityNames: LiveData<List<String>> = weatherLocalDataSource.observeCitiesNames()
    val cityNames: LiveData<List<String>> = _cityNames


}