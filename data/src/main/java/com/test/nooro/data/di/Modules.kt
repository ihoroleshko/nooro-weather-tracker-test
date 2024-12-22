package com.test.nooro.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.test.nooro.data.datasource.LocalWeatherDataSource
import com.test.nooro.data.datasource.RemoteWeatherDataSource
import com.test.nooro.data.network.Api
import com.test.nooro.data.preferences.DataStoreManager
import com.test.nooro.data.repository.WeatherRepositoryImpl
import com.test.nooro.domain.core.ConfigProvider
import com.test.nooro.domain.core.WeatherRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun provideHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    return OkHttpClient().newBuilder().apply {
        addInterceptor(loggingInterceptor)
        readTimeout(30, TimeUnit.SECONDS)
        connectTimeout(30, TimeUnit.SECONDS)
    }.build()
}

fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

fun provideConverterFactory(moshi: Moshi): MoshiConverterFactory {
    return MoshiConverterFactory.create(moshi)
}

fun provideRetrofit(
    configProvider: ConfigProvider,
    httpClient: OkHttpClient,
    converterFactory: MoshiConverterFactory
): Retrofit = Retrofit.Builder().baseUrl(configProvider.getBaseUrl()).client(httpClient)
    .addConverterFactory(converterFactory).build()

fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

val Context.weatherDataStore: DataStore<Preferences> by preferencesDataStore(name = "weather_preferences")

val dataModule = module {
    singleOf(::provideHttpClient)
    singleOf(::provideMoshi)
    singleOf(::provideConverterFactory)
    singleOf(::provideRetrofit)
    singleOf(::provideApi)
    singleOf(::WeatherRepositoryImpl) { bind<WeatherRepository>() }
    single { get<Context>().weatherDataStore }
    singleOf(::DataStoreManager)
    singleOf(::RemoteWeatherDataSource)
    singleOf(::LocalWeatherDataSource)
}