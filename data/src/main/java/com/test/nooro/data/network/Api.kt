package com.test.nooro.data.network

import com.test.nooro.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("v1/current.json")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("key") apiKey: String
    ): Response<WeatherResponse>
}