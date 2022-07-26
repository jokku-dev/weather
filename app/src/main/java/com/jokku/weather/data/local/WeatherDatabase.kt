package com.jokku.weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jokku.weather.data.local.entity.Weather

@Database(entities = [Weather::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}