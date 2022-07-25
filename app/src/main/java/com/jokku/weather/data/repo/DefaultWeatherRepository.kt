package com.jokku.weather.data.repo

import com.jokku.weather.data.local.WeatherDao
import com.jokku.weather.data.local.entity.Weather

class DefaultWeatherRepository(
    private val weatherDao: WeatherDao
): WeatherRepository {

    override suspend fun insertWeather(weather: Weather) = weatherDao.insertWeather(weather)

    override fun getCitiesNames() = weatherDao.getCitiesNames()

    override fun getCitySizeByName(city: String) = weatherDao.getCitySizeByName(city)

    override fun getWinterTemps(name: String, size: String) = weatherDao.getWinterTemps(name, size)

    override fun getSpringTemps(name: String, size: String) = weatherDao.getSpringTemps(name, size)

    override fun getSummerTemps(name: String, size: String) = weatherDao.getSummerTemps(name, size)

    override fun getAutumnTemps(name: String, size: String) = weatherDao.getAutumnTemps(name, size)

}