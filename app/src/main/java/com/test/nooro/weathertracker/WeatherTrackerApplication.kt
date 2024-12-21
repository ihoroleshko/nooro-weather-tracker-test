package com.test.nooro.weathertracker

import android.app.Application
import com.test.nooro.weathertracker.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherTrackerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherTrackerApplication)
            modules(appModule)
        }
    }
}