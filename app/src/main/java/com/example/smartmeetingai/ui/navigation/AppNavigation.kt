package com.example.smartmeetingai.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartmeetingai.ui.home.HomeScreen

object Routes {
    const val HOME = "home"

    const val NOTE = "note"

    const val SUMMARY = "summary"
}

@Composable
fun AppNavigation()  {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
    ) {
        composable(Routes.HOME) {
            HomeScreen()
        }
    }
}
