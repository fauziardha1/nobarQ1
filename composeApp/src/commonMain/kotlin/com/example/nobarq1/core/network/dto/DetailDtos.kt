package com.example.nobarq1.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto(
    val id: Int,
    val title: String,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("poster_path") val posterPath: String?,
    val overview: String,
    @SerialName("vote_average") val voteAverage: Double,
    val genres: List<GenreDto>,
    val runtime: Int?,
    @SerialName("release_date") val releaseDate: String
)

@Serializable
data class VideoResponseDto(
    val results: List<VideoDto>
)

@Serializable
data class VideoDto(
    val id: String,
    val key: String,
    val site: String,
    val type: String
)
