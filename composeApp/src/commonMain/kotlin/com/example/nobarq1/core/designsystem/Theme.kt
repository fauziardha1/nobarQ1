package com.example.nobarq1.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = NetflixRed,
    secondary = NetflixRed,
    tertiary = LightGrey,
    background = PureBlack,
    surface = DarkGrey,
    onPrimary = White,
    onSecondary = White,
    onTertiary = PureBlack,
    onBackground = White,
    onSurface = White,
)

@Composable
fun NobarTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = NobarTypography,
        content = content
    )
}
