package com.example.nobarq1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.nobarq1.core.designsystem.NobarTheme
import com.example.nobarq1.features.home.ui.HomeScreen

@Composable
fun App() {
    NobarTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                modifier = Modifier.safeContentPadding(),
                containerColor = androidx.compose.material3.MaterialTheme.colorScheme.background
            ) { innerPadding ->
                HomeScreen(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}