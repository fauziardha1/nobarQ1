package com.example.nobarq1.features.home.data

import com.example.nobarq1.features.home.domain.Genre
import com.example.nobarq1.features.home.domain.Movie
import com.example.nobarq1.features.home.domain.MovieDetail

interface MovieRepository {
    suspend fun getGenres(): List<Genre>
    suspend fun getMoviesByGenre(genreId: Int): List<Movie>
    suspend fun searchMovies(query: String): List<Movie>
    suspend fun getMovieDetail(movieId: Int): MovieDetail
    suspend fun isFavorite(movieId: Int): Boolean
    suspend fun toggleFavorite(movie: MovieDetail)
    suspend fun getFavorites(): List<Movie>
}
