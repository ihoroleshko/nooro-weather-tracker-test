package com.test.nooro.data.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.test.nooro.data.network.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

fun provideConverterFactory(): MoshiConverterFactory {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    return MoshiConverterFactory.create(moshi)
}

fun provideRetrofit(
    httpClient: OkHttpClient, converterFactory: MoshiConverterFactory
): Retrofit = Retrofit.Builder().baseUrl("").client(httpClient)
    .addConverterFactory(converterFactory).build()

fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

val dataModule = module {
    singleOf(::provideHttpClient)
    singleOf(::provideConverterFactory)
    singleOf(::provideRetrofit)
    singleOf(::provideApi)
}