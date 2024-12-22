package com.test.nooro.domain.core

interface ConfigProvider {

    fun getBaseUrl(): String

    fun getApiKey(): String
}