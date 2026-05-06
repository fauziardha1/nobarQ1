package com.example.nobarq1.features.home.domain

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val overview: String
)

data class Genre(
    val id: Int,
    val name: String
)
