package com.test.nooro.weathertracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.test.nooro.weathertracker.ui.navigation.WeatherTrackerNavHost

class WeatherTrackerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UI()
        }
    }

    @Composable
    private fun UI() {
        Surface(modifier = Modifier.fillMaxSize()) {
            WeatherTrackerNavHost()
        }
    }
}