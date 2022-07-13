package com.jokku.weather.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun toString(array: List<String>): String {
        return array.joinToString(",")
    }

    @TypeConverter
    fun toArray(string: String): List<String> {
        return string.split(",").toList()
    }
}