package com.jokku.weather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jokku.weather.data.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(weather: Weather)

    @Query("SELECT name FROM weather")
    suspend fun getCitiesNames(): List<String>

    @Query("SELECT size FROM weather WHERE name = :city")
    suspend fun getCitySizeByName(city: String): String

    @Query("SELECT `temp` FROM weather WHERE name = :name AND season = :season")
    suspend fun getTemperaturesByNameAndSeason(name: String, season: String): List<String>
}