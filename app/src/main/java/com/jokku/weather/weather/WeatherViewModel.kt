package com.jokku.weather.weather

import androidx.lifecycle.*
import com.jokku.weather.data.source.WeatherDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherLocalDataSource: WeatherDataSource
) : ViewModel() {

//    private val _cities: LiveData<List<String>> = weatherLocalDataSource.observeCitiesNames()
//    val cities: LiveData<List<String>> = _cities
//
//    private val _citySize: LiveData<String> = weatherLocalDataSource.getCitySizeByName()
//    val cities: LiveData<List<String>> = _cities




}