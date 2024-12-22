package com.test.nooro.weathertracker.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nooro.data.repository.NetworkRepository
import com.test.nooro.domain.model.DataState
import com.test.nooro.domain.model.Weather
import com.test.nooro.domain.usecase.CacheWeatherUseCase
import com.test.nooro.domain.usecase.GetCachedWeatherUseCase
import com.test.nooro.domain.usecase.WeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val weatherUseCase: WeatherUseCase,
    private val cacheWeatherUseCase: CacheWeatherUseCase,
    private val getCachedWeatherUseCase: GetCachedWeatherUseCase,
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _weather = MutableStateFlow<DataState<Weather>>(DataState.Idle())
    val weather = _weather.asStateFlow()

    var savedWeather = mutableStateOf<Weather?>(null)
        private set

    fun getWeather(city: String) {
        savedWeather.value = null
        viewModelScope.launch {
            weatherUseCase.execute(city).collectLatest(_weather::value::set)
        }
    }

    fun saveWeather(weather: Weather) {
        savedWeather.value = weather
        viewModelScope.launch {
            cacheWeatherUseCase.execute(weather)
        }
    }

    fun updateCachedWeather() {
        viewModelScope.launch {
            val cachedWeather = getCachedWeatherUseCase.execute() ?: return@launch
            if (networkRepository.isNetworkAvailable()) {
                weatherUseCase.execute(cachedWeather.city).collectLatest {
                    if (it is DataState.Success) {
                        savedWeather.value = it.data
                    } else {
                        savedWeather.value = cachedWeather
                    }
                }
            } else {
                savedWeather.value = cachedWeather
            }
        }
    }
}