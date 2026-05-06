package com.example.nobarq1.features.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import com.example.nobarq1.core.designsystem.NetflixRed
import com.example.nobarq1.core.designsystem.PureBlack
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onAnimationFinished: () -> Unit) {
    val scale = remember { Animatable(0.5f) }

    LaunchedEffect(Unit) {
        // Start scaling animation
        scale.animateTo(
            targetValue = 1.0f,
            animationSpec = tween(durationMillis = 1000)
        )
        // Wait for a total of at least 2 seconds
        delay(1000) 
        onAnimationFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PureBlack),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "NobarQ1",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Black,
                color = NetflixRed
            ),
            modifier = Modifier.scale(scale.value)
        )
    }
}
