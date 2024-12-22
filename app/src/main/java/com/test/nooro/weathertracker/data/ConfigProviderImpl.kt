package com.test.nooro.weathertracker.data

import com.test.nooro.domain.core.ConfigProvider
import com.test.nooro.weathertracker.BuildConfig

class ConfigProviderImpl : ConfigProvider {

    override fun getBaseUrl(): String = BuildConfig.BASE_URL

    override fun getApiKey(): String = BuildConfig.API_KEY
}