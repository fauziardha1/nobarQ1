package com.example.nobarq1

import androidx.compose.ui.window.ComposeUIViewController
import com.example.nobarq1.core.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}