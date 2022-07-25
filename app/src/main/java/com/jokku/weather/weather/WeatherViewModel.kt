package com.jokku.weather.weather

import androidx.lifecycle.*
import com.jokku.weather.data.repo.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val emptyLiveData: LiveData<List<String>> by lazy { MutableLiveData(emptyList()) }


    fun getCitiesNames() = repository.getCitiesNames()

    fun getCitySizeByName(chosenCity: String) = repository.getCitySizeByName(chosenCity)

    fun getAverageSeasonTemp(chosenCity: String, chosenSeason: String): LiveData<String> {

        val liveData = when (chosenSeason) {
            "Winter" -> repository.getWinterTemps(chosenCity, chosenSeason)
            "Spring" -> repository.getSpringTemps(chosenCity, chosenSeason)
            "Summer" -> repository.getSummerTemps(chosenCity, chosenSeason)
            "Autumn" -> repository.getAutumnTemps(chosenCity, chosenSeason)
            else -> emptyLiveData
        }

        val averageTemp = Transformations.map(liveData) { list ->
            if (list.isNotEmpty()) { list.sumOf { it.toInt() }.div(list.size).toString() }
            else "enter correct season"
        }
        return averageTemp
    }
}