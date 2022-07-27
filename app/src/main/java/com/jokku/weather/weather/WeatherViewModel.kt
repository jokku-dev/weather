package com.jokku.weather.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jokku.weather.data.repo.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val emptyLiveData: MutableLiveData<String> by lazy { MutableLiveData() }
    private val newTemp: MutableLiveData<String> by lazy { MutableLiveData() }

    fun getCitiesNames() = repository.getCitiesNames()

    fun getCitySizeByName(chosenCity: String) = repository.getCitySizeByName(chosenCity)

    fun getAverageSeasonTemp(chosenCity: String, chosenSeason: String): LiveData<String> {
        val liveData = when (chosenSeason) {
            "Winter" -> repository.getWinterTemps(chosenCity)
            "Spring" -> repository.getSpringTemps(chosenCity)
            "Summer" -> repository.getSummerTemps(chosenCity)
            "Autumn" -> repository.getAutumnTemps(chosenCity)
            else -> emptyLiveData
        }
        return Transformations.map(liveData) { temps ->
            calcAverage(temps)
        }
    }

    fun changeTempFormat(temp: String, tempFormat: String): MutableLiveData<String> {
        newTemp.value = when (tempFormat) {
            "Celsius" -> temp.toInt()
            "Fahrenheit" -> (temp.toDouble() * 1.8 + 32).toInt()
            "Kelvin" -> (temp.toDouble() + 273.15).toInt()
            else -> ""
        }.toString()
        return newTemp
    }

    private fun calcAverage(temps: String): String {
        return if (temps.isNotEmpty()) {
            temps.split(",").sumOf { it.toInt() }.also {
                if (it != 0) it.div(3)
            }.toString()
        } else {
            ""
        }
    }

}