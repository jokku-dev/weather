package com.jokku.weather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jokku.weather.data.City

@Dao
interface CitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: City)

    @Query("SELECT * FROM cities")
    suspend fun getCities() : List<City>
}