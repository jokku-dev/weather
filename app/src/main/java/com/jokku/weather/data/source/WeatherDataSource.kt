package com.jokku.weather.data.source

import androidx.lifecycle.LiveData
import com.jokku.weather.data.Weather

interface WeatherDataSource {
    fun observeCitiesNames(): LiveData<List<String>>
    suspend fun insertWeather(weather: Weather)
    suspend fun getCitiesNames(): List<String>
    suspend fun getCitySizeByName(city: String): String
    suspend fun getWinterTemps(name: String, size: String): List<String>
    suspend fun getSpringTemps(name: String, size: String): List<String>
    suspend fun getSummerTemps(name: String, size: String): List<String>
    suspend fun getAutumnTemps(name: String, size: String): List<String>
}