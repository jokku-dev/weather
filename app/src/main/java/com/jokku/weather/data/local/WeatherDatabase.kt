package com.jokku.weather.data.local

import androidx.room.Database
import com.jokku.weather.data.Weather

@Database(entities = [Weather::class], version = 1, exportSchema = false)
abstract class WeatherDatabase {

    abstract fun citiesDao(): WeatherDao
}