package com.jokku.weather.data.source

import com.jokku.weather.data.Weather

interface WeatherDataSource {
    suspend fun insertCity(weather: Weather)
    suspend fun getCitiesNames(): List<String>
    suspend fun getCitySizeByName(city: String): String
    suspend fun getTemperaturesByNameAndSeason(name: String, season: String): List<String>
}