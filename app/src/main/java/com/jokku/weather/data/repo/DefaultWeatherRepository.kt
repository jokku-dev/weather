package com.jokku.weather.data.repo

import com.jokku.weather.data.local.WeatherDao
import com.jokku.weather.data.local.entity.Weather

class DefaultWeatherRepository(
    private val weatherDao: WeatherDao
): WeatherRepository {

    override suspend fun insertWeather(weather: Weather) = weatherDao.insertWeather(weather)

    override fun getCitiesNames() = weatherDao.getCitiesNames()

    override fun getCitySizeByName(city: String) = weatherDao.getCitySizeByName(city)

    override fun getWinterTemps(name: String) = weatherDao.getWinterTemps(name)

    override fun getSpringTemps(name: String) = weatherDao.getSpringTemps(name)

    override fun getSummerTemps(name: String) = weatherDao.getSummerTemps(name)

    override fun getAutumnTemps(name: String) = weatherDao.getAutumnTemps(name)

}