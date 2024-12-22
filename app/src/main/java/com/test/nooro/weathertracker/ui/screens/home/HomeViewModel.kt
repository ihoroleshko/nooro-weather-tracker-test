package com.test.nooro.weathertracker.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nooro.domain.model.DataState
import com.test.nooro.domain.model.Weather
import com.test.nooro.domain.usecase.WeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val weatherUseCase: WeatherUseCase
) : ViewModel() {

    private val _weather = MutableStateFlow<DataState<Weather>>(DataState.Idle())
    val weather = _weather.asStateFlow()

    fun getWeather(city: String) {
        viewModelScope.launch {
            weatherUseCase.execute(city).collectLatest(_weather::value::set)
        }
    }
}