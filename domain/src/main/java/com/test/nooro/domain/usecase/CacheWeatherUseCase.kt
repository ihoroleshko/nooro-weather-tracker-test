package com.test.nooro.domain.usecase

import com.test.nooro.domain.core.WeatherRepository
import com.test.nooro.domain.model.Weather

class CacheWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {

    suspend fun execute(weather: Weather) {
        weatherRepository.cacheWeather(weather)
    }
}