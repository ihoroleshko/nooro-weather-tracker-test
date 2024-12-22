package com.test.nooro.domain.core

import com.test.nooro.domain.model.DataState
import com.test.nooro.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeather(city: String): Flow<DataState<Weather>>

    suspend fun cacheWeather(weather: Weather)

    suspend fun getCachedWeather(): Weather?
}