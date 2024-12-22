package com.test.nooro.data.model

import com.squareup.moshi.Json

data class Location(
    @Json(name = "name") val city: String
)