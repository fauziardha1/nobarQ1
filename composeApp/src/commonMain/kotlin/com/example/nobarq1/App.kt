package com.example.nobarq1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.nobarq1.core.designsystem.NobarTheme
import com.example.nobarq1.features.auth.ui.LoginScreen
import com.example.nobarq1.features.auth.ui.ProfileSelectionScreen
import com.example.nobarq1.features.detail.ui.DetailScreen
import com.example.nobarq1.features.home.ui.HomeScreen
import com.example.nobarq1.features.search.ui.SearchScreen
import com.example.nobarq1.features.splash.SplashScreen

sealed class Screen {
    object Splash : Screen()
    object Login : Screen()
    object ProfileSelection : Screen()
    object Home : Screen()
    object Search : Screen()
    data class Detail(val movieId: Int) : Screen()
}

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }

    NobarTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
            when (val screen = currentScreen) {
                is Screen.Splash -> {
                    SplashScreen(onAnimationFinished = {
                        currentScreen = Screen.Login
                    })
                }
                is Screen.Login -> {
                    LoginScreen(onLoginSuccess = {
                        currentScreen = Screen.ProfileSelection
                    })
                }
                is Screen.ProfileSelection -> {
                    ProfileSelectionScreen(onProfileSelected = {
                        currentScreen = Screen.Home
                    })
                }
                is Screen.Home -> {
                    HomeScreen(
                        onSearchClick = { currentScreen = Screen.Search },
                        onMovieClick = { movieId -> currentScreen = Screen.Detail(movieId) }
                    )
                }
                is Screen.Search -> {
                    SearchScreen(
                        onBack = { currentScreen = Screen.Home },
                        onMovieClick = { movieId -> currentScreen = Screen.Detail(movieId) }
                    )
                }
                is Screen.Detail -> {
                    DetailScreen(
                        movieId = screen.movieId,
                        onBack = { currentScreen = Screen.Home }
                    )
                }
            }
        }
    }
}
