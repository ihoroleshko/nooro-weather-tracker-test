package com.test.nooro.data.datasource

import com.test.nooro.data.model.WeatherResponse
import com.test.nooro.data.network.Api
import com.test.nooro.domain.core.ConfigProvider
import retrofit2.Response

class RemoteWeatherDataSource(
    private val api: Api, private val configProvider: ConfigProvider
) {

    suspend fun getWeather(city: String): Response<WeatherResponse> {
        return api.getWeather(city, configProvider.getApiKey())
    }
}