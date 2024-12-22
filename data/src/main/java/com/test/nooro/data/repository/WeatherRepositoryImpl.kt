package com.test.nooro.data.repository

import com.test.nooro.data.datasource.LocalWeatherDataSource
import com.test.nooro.data.datasource.RemoteWeatherDataSource
import com.test.nooro.data.mapper.WeatherMapper
import com.test.nooro.domain.core.WeatherRepository
import com.test.nooro.domain.model.DataState
import com.test.nooro.domain.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepositoryImpl(
    private val mapper: WeatherMapper,
    private val remoteDataSource: RemoteWeatherDataSource,
    private val localDataSource: LocalWeatherDataSource,
) : WeatherRepository {

    override suspend fun getWeather(city: String): Flow<DataState<Weather>> = flow {
        emit(DataState.Loading())

        val response = remoteDataSource.getWeather(city)
        val body = response.body()
        if (response.isSuccessful && body != null) {
            emit(DataState.Success(mapper.toEntity(body)))
        } else {
            emit(DataState.Error("Unsuccessful"))
        }
    }.catch {
        emit(DataState.Error(it.message ?: ""))
    }.flowOn(Dispatchers.IO)

    override suspend fun cacheWeather(weather: Weather) {
        localDataSource.cacheWeather(weather)
    }

    override suspend fun getCachedWeather(): Weather? {
        return localDataSource.getWeather()
    }
}