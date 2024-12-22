package com.test.nooro.domain.core

interface WeatherRepository {

    suspend fun getWeather(city: String): Any
}