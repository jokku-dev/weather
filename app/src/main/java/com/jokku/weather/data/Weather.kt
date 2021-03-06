package com.jokku.weather.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "weather")
@TypeConverters(Converters::class)
data class Weather(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String?,
    val size: String?,
    val season: String?,
    val temp: List<String>
) {
}