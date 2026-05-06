package com.example.nobarq1

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform