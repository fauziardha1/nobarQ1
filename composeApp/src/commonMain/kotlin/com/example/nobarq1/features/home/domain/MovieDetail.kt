package com.example.nobarq1.features.home.domain

data class MovieDetail(
    val id: Int,
    val title: String,
    val backdropPath: String?,
    val posterPath: String?,
    val overview: String,
    val voteAverage: Double,
    val genres: List<String>,
    val runtime: String,
    val releaseDate: String,
    val trailerKey: String?
)
