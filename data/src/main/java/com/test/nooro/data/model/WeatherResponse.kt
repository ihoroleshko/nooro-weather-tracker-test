package com.test.nooro.data.model

import com.squareup.moshi.Json

data class WeatherResponse(
    val location: Location,
    @Json(name = "current") val weatherDetails: WeatherDetails
)