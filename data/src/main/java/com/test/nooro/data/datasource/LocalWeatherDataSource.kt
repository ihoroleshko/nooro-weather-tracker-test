package com.test.nooro.data.datasource

import androidx.datastore.preferences.core.stringPreferencesKey
import com.squareup.moshi.Moshi
import com.test.nooro.data.preferences.DataStoreManager
import com.test.nooro.domain.model.Weather

class LocalWeatherDataSource(
    private val dataStoreManager: DataStoreManager,
    moshi: Moshi
) {

    private val WEATHER_KEY = stringPreferencesKey("saved_weather")
    private val adapter = moshi.adapter(Weather::class.java)

    suspend fun cacheWeather(weather: Weather) {
        val json = adapter.toJson(weather)
        dataStoreManager.saveData(WEATHER_KEY, json)
    }

    suspend fun getWeather(): Weather? {
        val json = dataStoreManager.getData(WEATHER_KEY)
        return json?.let(adapter::fromJson)
    }
}