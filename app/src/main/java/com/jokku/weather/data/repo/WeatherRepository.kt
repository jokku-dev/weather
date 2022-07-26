package com.jokku.weather.data.repo

import androidx.lifecycle.LiveData
import com.jokku.weather.data.local.entity.Weather

interface WeatherRepository {
    suspend fun insertWeather(weather: Weather)
    fun getCitiesNames(): LiveData<List<String>>
    fun getCitySizeByName(city: String): LiveData<String>
    fun getWinterTemps(name: String): LiveData<String>
    fun getSpringTemps(name: String): LiveData<String>
    fun getSummerTemps(name: String): LiveData<String>
    fun getAutumnTemps(name: String): LiveData<String>
}