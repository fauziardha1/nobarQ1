package com.example.nobarq1.features.home.data

import com.example.nobarq1.features.home.domain.Genre
import com.example.nobarq1.features.home.domain.Movie

interface MovieRepository {
    suspend fun getGenres(): List<Genre>
    suspend fun getMoviesByGenre(genreId: Int): List<Movie>
    suspend fun searchMovies(query: String): List<Movie>
}
