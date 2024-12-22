package com.test.nooro.data.repository

import com.test.nooro.data.mapper.WeatherMapper
import com.test.nooro.data.network.Api
import com.test.nooro.domain.core.ConfigProvider
import com.test.nooro.domain.core.WeatherRepository
import com.test.nooro.domain.model.DataState
import com.test.nooro.domain.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepositoryImpl(
    private val api: Api,
    private val configProvider: ConfigProvider,
    private val mapper: WeatherMapper
) : WeatherRepository {

    override suspend fun getWeather(city: String): Flow<DataState<Weather>> = flow {
        emit(DataState.Loading())

        val response = api.getWeather(city, configProvider.getApiKey())
        val body = response.body()
        if (response.isSuccessful && body != null) {
            emit(DataState.Success(mapper.toEntity(body)))
        } else {
            emit(DataState.Error("Unsuccessful"))
        }
    }.catch {
        emit(DataState.Error(it.message ?: ""))
    }.flowOn(Dispatchers.IO)
}