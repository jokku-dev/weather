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

    private val emptyLiveData: LiveData<String> by lazy { MutableLiveData("") }

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
            if (temps.isNotEmpty()) {
                temps.split(",").sumOf { it.toInt() }.also {
                        if (it != 0) it.div(3)
                    }.toString()
                } else {
                ""
            }
        }
        /*val averageTemp: LiveData<String> = Transformations.map(liveData) { temps ->
            calcAverageTemp(temps)
        }
        return averageTemp*/
    }

    private fun calcAverageTemp(temps: String): String {
        return if (temps.isNotEmpty()) {
            temps.trim().split(",").also { list ->
                list.sumOf { it.toInt() }.also {
                    if (it != 0) it.div(list.size)
                }
            }.toString()
        } else ""
    }
}