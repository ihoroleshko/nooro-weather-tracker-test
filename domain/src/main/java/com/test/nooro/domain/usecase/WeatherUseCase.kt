package com.test.nooro.domain.usecase

import com.test.nooro.domain.core.WeatherRepository
import com.test.nooro.domain.model.DataState
import com.test.nooro.domain.model.Weather
import kotlinx.coroutines.flow.Flow

class WeatherUseCase(
    private val repository: WeatherRepository
) {

    suspend fun execute(city: String): Flow<DataState<Weather>> = repository.getWeather(city)
}