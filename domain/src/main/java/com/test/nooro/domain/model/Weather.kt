package com.test.nooro.domain.model

data class Weather(
    val city: String,
    val temperature: Float,
    val weatherIconUrl: String,
    val humidity: Int,
    val uvIndex: Int,
    val feelsLikeFahrenheit: Float
)