package com.test.nooro.domain.usecase

import com.test.nooro.domain.core.WeatherRepository
import com.test.nooro.domain.model.Weather

class GetCachedWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {

    suspend fun execute(): Weather? = weatherRepository.getCachedWeather()
}