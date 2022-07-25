package com.jokku.weather.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jokku.weather.data.Converters
import java.util.*

@Entity(tableName = "weather")
@TypeConverters(Converters::class)
data class Weather(
    val name: String,
    val size: String,
    val winterTemps: List<String>,
    val springTemps: List<String>,
    val summerTemps: List<String>,
    val autumnTemps: List<String>
) {
    @PrimaryKey var id: String = UUID.randomUUID().toString()
}