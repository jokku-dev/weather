package com.jokku.weather.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "weather")
@TypeConverters(Converters::class)
data class Weather @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String?,
    val size: String?,
    val winterTemps: List<String>?,
    val springTemps: List<String>?,
    val summerTemps: List<String>?,
    val autumnTemps: List<String>?
) {
}