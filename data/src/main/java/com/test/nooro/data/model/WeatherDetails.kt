package com.test.nooro.data.model

import com.squareup.moshi.Json

data class WeatherDetails(
    @Json(name = "temp_f") val temperatureFahrenheit: Float,
    @Json(name = "condition") val weatherCondition: WeatherCondition,
    val humidity: Int,
    @Json(name = "uv") val uvIndex: Float,
    @Json(name = "feelslike_f") val feelsLikeFahrenheit: Float,
)