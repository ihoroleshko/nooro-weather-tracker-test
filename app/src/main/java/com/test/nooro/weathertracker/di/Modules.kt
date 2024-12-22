package com.test.nooro.weathertracker.di

import com.test.nooro.data.mapper.WeatherMapper
import com.test.nooro.domain.core.ConfigProvider
import com.test.nooro.domain.usecase.CacheWeatherUseCase
import com.test.nooro.domain.usecase.GetCachedWeatherUseCase
import com.test.nooro.domain.usecase.WeatherUseCase
import com.test.nooro.weathertracker.data.ConfigProviderImpl
import com.test.nooro.weathertracker.ui.screens.home.HomeViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    singleOf(::ConfigProviderImpl) { bind<ConfigProvider>() }
    singleOf(::WeatherUseCase)
    singleOf(::GetCachedWeatherUseCase)
    singleOf(::CacheWeatherUseCase)

    factoryOf(::WeatherMapper)

    viewModelOf(::HomeViewModel)
}