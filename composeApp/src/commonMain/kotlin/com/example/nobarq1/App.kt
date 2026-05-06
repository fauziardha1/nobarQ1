package com.example.nobarq1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.nobarq1.core.designsystem.NobarTheme
import com.example.nobarq1.features.home.ui.HomeScreen
import com.example.nobarq1.features.search.ui.SearchScreen
import com.example.nobarq1.features.splash.SplashScreen

sealed class Screen {
    object Splash : Screen()
    object Home : Screen()
    object Search : Screen()
}

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }

    NobarTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
            when (currentScreen) {
                is Screen.Splash -> {
                    SplashScreen(onAnimationFinished = {
                        currentScreen = Screen.Home
                    })
                }
                is Screen.Home -> {
                    HomeScreen(
                        onSearchClick = { currentScreen = Screen.Search }
                    )
                }
                is Screen.Search -> {
                    SearchScreen(
                        onBack = { currentScreen = Screen.Home }
                    )
                }
            }
        }
    }
}
