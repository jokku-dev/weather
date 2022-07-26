package com.jokku.weather.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jokku.weather.data.local.entity.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: Weather)

    @Query("SELECT name FROM weather")
    fun getCitiesNames(): LiveData<List<String>>

    @Query("SELECT size FROM weather WHERE name = :city")
    fun getCitySizeByName(city: String): LiveData<String>
    
    @Query("SELECT winterTemps FROM weather WHERE name = :name")
    fun getWinterTemps(name: String): LiveData<String>

    @Query("SELECT springTemps FROM weather WHERE name = :name")
    fun getSpringTemps(name: String): LiveData<String>

    @Query("SELECT summerTemps FROM weather WHERE name = :name")
    fun getSummerTemps(name: String): LiveData<String>

    @Query("SELECT autumnTemps FROM weather WHERE name = :name")
    fun getAutumnTemps(name: String): LiveData<String>
}