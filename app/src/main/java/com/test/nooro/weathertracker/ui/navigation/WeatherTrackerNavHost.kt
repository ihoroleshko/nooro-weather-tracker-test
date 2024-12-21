package com.test.nooro.weathertracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.nooro.weathertracker.ui.screens.home.Home

@Composable
fun WeatherTrackerNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.HOME) {
        composable(Destination.HOME) {
            Home()
        }
    }
}