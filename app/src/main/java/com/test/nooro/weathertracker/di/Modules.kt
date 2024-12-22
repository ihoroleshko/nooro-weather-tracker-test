package com.test.nooro.weathertracker.di

import com.test.nooro.domain.core.ConfigProvider
import com.test.nooro.weathertracker.data.ConfigProviderImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {

    singleOf(::ConfigProviderImpl) { bind<ConfigProvider>() }
}