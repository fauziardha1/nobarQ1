package com.example.nobarq1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.nobarq1.core.designsystem.NobarTheme
import com.example.nobarq1.features.auth.ui.LoginScreen
import com.example.nobarq1.features.auth.ui.ProfileSelectionScreen
import com.example.nobarq1.features.detail.ui.DetailScreen
import com.example.nobarq1.features.home.ui.HomeScreen
import com.example.nobarq1.features.liked.ui.LikedScreen
import com.example.nobarq1.features.search.ui.SearchScreen
import com.example.nobarq1.features.splash.SplashScreen
import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object Login

@Serializable
object Profile

@Serializable
object Home

@Serializable
object Search

@Serializable
object Liked

@Serializable
data class Detail(val movieId: Int)

@Composable
fun App() {
    val navController = rememberNavController()

    NobarTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
            NavHost(navController = navController, startDestination = Splash) {
                composable<Splash> {
                    SplashScreen(onAnimationFinished = {
                        navController.navigate(Login) {
                            popUpTo<Splash> { inclusive = true }
                        }
                    })
                }
                composable<Login> {
                    LoginScreen(onLoginSuccess = {
                        navController.navigate(Profile) {
                            popUpTo<Login> { inclusive = true }
                        }
                    })
                }
                composable<Profile> {
                    ProfileSelectionScreen(onProfileSelected = {
                        navController.navigate(Home) {
                            popUpTo<Profile> { inclusive = true }
                        }
                    })
                }
                composable<Home> {
                    HomeScreen(
                        onSearchClick = { navController.navigate(Search) },
                        onMovieClick = { movieId -> navController.navigate(Detail(movieId)) },
                        onLikedClick = { navController.navigate(Liked) }
                    )
                }
                composable<Search> {
                    SearchScreen(
                        onBack = { navController.popBackStack() },
                        onMovieClick = { movieId -> navController.navigate(Detail(movieId)) }
                    )
                }
                composable<Liked> {
                    LikedScreen(
                        onBack = { navController.popBackStack() },
                        onMovieClick = { movieId -> navController.navigate(Detail(movieId)) }
                    )
                }
                composable<Detail> { backStackEntry ->
                    val detail: Detail = backStackEntry.toRoute()
                    DetailScreen(
                        movieId = detail.movieId,
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
