package com.test.nooro.data.repository

import com.test.nooro.data.network.Api
import com.test.nooro.domain.core.ConfigProvider
import com.test.nooro.domain.core.WeatherRepository

class WeatherRepositoryImpl(
    private val api: Api,
    private val configProvider: ConfigProvider
) : WeatherRepository {

    override suspend fun getWeather(city: String): Any {
        return api.getWeather(city, configProvider.getApiKey())
    }
}