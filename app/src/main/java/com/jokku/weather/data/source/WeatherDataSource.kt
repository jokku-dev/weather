package com.jokku.weather.data.source

import androidx.lifecycle.LiveData
import com.jokku.weather.data.Weather

interface WeatherDataSource {
    fun observeCitiesNames(): LiveData<List<String>>
    suspend fun insertWeather(weather: Weather)
    suspend fun getCitiesNames(): List<String>
    suspend fun getCitySizeByName(city: String): String
    suspend fun getTemperaturesByNameAndSeason(name: String, season: String): List<String>
}