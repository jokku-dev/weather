package com.jokku.weather.data.repo

import androidx.lifecycle.LiveData
import com.jokku.weather.data.local.entity.Weather

interface WeatherRepository {
    suspend fun insertWeather(weather: Weather)
    fun getCitiesNames(): LiveData<List<String>>
    fun getCitySizeByName(city: String): LiveData<String>
    fun getWinterTemps(name: String, size: String): LiveData<List<String>>
    fun getSpringTemps(name: String, size: String): LiveData<List<String>>
    fun getSummerTemps(name: String, size: String): LiveData<List<String>>
    fun getAutumnTemps(name: String, size: String): LiveData<List<String>>
}