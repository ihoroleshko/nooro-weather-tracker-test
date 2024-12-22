package com.test.nooro.data.model

import com.squareup.moshi.Json

data class WeatherDetails(
    val temperature: Float,
    @Json(name = "condition") val weatherCondition: WeatherCondition,
    val humidity: Int,
    @Json(name = "uv") val uvIndex: Int,
    @Json(name = "feelslike_f") val feelsLikeFahrenheit: Float,
)