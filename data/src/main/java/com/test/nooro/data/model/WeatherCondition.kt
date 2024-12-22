package com.test.nooro.data.model

import com.squareup.moshi.Json

data class WeatherCondition(
    @Json(name = "icon") val iconUrl: String
)