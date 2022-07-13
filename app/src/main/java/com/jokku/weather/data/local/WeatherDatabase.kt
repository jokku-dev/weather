package com.jokku.weather.data.local

import androidx.room.Database
import com.jokku.weather.data.City

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class WeatherDatabase {

    abstract fun citiesDao(): CitiesDao
}