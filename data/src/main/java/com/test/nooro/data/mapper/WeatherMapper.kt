package com.test.nooro.data.mapper

import com.test.nooro.data.model.WeatherResponse
import com.test.nooro.domain.model.Weather

class WeatherMapper {

    fun toEntity(response: WeatherResponse): Weather = Weather(
        city = response.location.city,
        temperature = response.weatherDetails.temperature,
        weatherIconUrl = response.weatherDetails.weatherCondition.iconUrl,
        humidity = response.weatherDetails.humidity,
        uvIndex = response.weatherDetails.uvIndex,
        feelsLikeFahrenheit = response.weatherDetails.feelsLikeFahrenheit
    )
}