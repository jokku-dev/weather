package com.jokku.weather.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jokku.weather.data.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: Weather)

    @Query("SELECT name FROM weather")
    fun observeCitiesNames(): LiveData<List<String>>

    @Query("SELECT name FROM weather")
    suspend fun getCitiesNames(): List<String>

    @Query("SELECT size FROM weather WHERE name = :city")
    suspend fun getCitySizeByName(city: String): String
    
    @Query("SELECT seasons FROM weather WHERE name = :city")
    suspend fun getSeasonsByName(city: String): List<String>

    @Query("SELECT temps FROM weather WHERE name = :name")
    suspend fun getWinterTemperaturesByNameAndSeason(name: String): List<String>
}