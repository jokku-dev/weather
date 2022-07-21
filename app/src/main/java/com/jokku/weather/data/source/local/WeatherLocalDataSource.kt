package com.jokku.weather.data.source.local

import androidx.lifecycle.LiveData
import com.jokku.weather.data.Weather
import com.jokku.weather.data.source.WeatherDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherLocalDataSource(
    private val weatherDao: WeatherDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): WeatherDataSource {

    override fun observeCitiesNames(): LiveData<List<String>> {
        return weatherDao.observeCitiesNames()
    }

    override suspend fun insertWeather(weather: Weather) = withContext(ioDispatcher) {
        weatherDao.insertWeather(weather)
    }

    override suspend fun getCitiesNames(): List<String> = withContext(ioDispatcher) {
        return@withContext weatherDao.getCitiesNames()
    }

    override suspend fun getCitySizeByName(city: String): String = withContext(ioDispatcher) {
        return@withContext weatherDao.getCitySizeByName(city)
    }

    override suspend fun getWinterTemps(name: String, size: String): List<String> = withContext(ioDispatcher) {
        return@withContext weatherDao.getWinterTemps(name, size)
    }

    override suspend fun getSpringTemps(name: String, size: String): List<String> = withContext(ioDispatcher) {
        return@withContext weatherDao.getSpringTemps(name, size)
    }

    override suspend fun getSummerTemps(name: String, size: String): List<String> = withContext(ioDispatcher) {
        return@withContext weatherDao.getSummerTemps(name, size)
    }

    override suspend fun getAutumnTemps(name: String, size: String): List<String> = withContext(ioDispatcher) {
        return@withContext weatherDao.getAutumnTemps(name, size)
    }
}