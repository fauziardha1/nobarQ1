package com.example.nobarq1.features.home.data

import com.example.nobarq1.core.network.dto.GenreResponse
import com.example.nobarq1.core.network.dto.MovieDetailDto
import com.example.nobarq1.core.network.dto.MovieResponse
import com.example.nobarq1.core.network.dto.VideoResponseDto
import com.example.nobarq1.database.NobarDatabase
import com.example.nobarq1.features.home.domain.Genre
import com.example.nobarq1.features.home.domain.Movie
import com.example.nobarq1.features.home.domain.MovieDetail
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class MovieRepositoryImpl(
    private val httpClient: HttpClient,
    private val database: NobarDatabase
) : MovieRepository {

    override suspend fun getGenres(): List<Genre> {
        val response: GenreResponse = httpClient.get("genre/movie/list").body()
        return response.genres.map { Genre(it.id, it.name) }
    }

    override suspend fun getMoviesByGenre(genreId: Int): List<Movie> {
        // Try local cache first
        val cached = database.nobarDatabaseQueries.getMoviesByGenre(genreId.toLong()).executeAsList()
        if (cached.isNotEmpty()) {
            return cached.map { Movie(it.id.toInt(), it.title, it.posterPath, it.overview) }
        }

        val response: MovieResponse = httpClient.get("discover/movie") {
            parameter("with_genres", genreId)
            parameter("sort_by", "popularity.desc")
        }.body()

        val movies = response.results.map { 
            Movie(it.id, it.title, it.posterPath, it.overview) 
        }

        // Save to cache
        movies.forEach { movie ->
            database.nobarDatabaseQueries.insertMovie(
                id = movie.id.toLong(),
                title = movie.title,
                posterPath = movie.posterPath,
                genreId = genreId.toLong(),
                overview = movie.overview
            )
        }

        return movies
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        val response: MovieResponse = httpClient.get("search/movie") {
            parameter("query", query)
        }.body()

        return response.results.map { 
            Movie(it.id, it.title, it.posterPath, it.overview) 
        }
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        val detailDto: MovieDetailDto = httpClient.get("movie/$movieId").body()
        val videoResponse: VideoResponseDto = httpClient.get("movie/$movieId/videos").body()
        
        val trailer = videoResponse.results.find { 
            it.site.lowercase() == "youtube" && it.type.lowercase() == "trailer" 
        } ?: videoResponse.results.find { it.site.lowercase() == "youtube" }

        return MovieDetail(
            id = detailDto.id,
            title = detailDto.title,
            backdropPath = detailDto.backdropPath,
            posterPath = detailDto.posterPath,
            overview = detailDto.overview,
            voteAverage = detailDto.voteAverage,
            genres = detailDto.genres.map { it.name },
            runtime = "${detailDto.runtime ?: 0} min",
            releaseDate = detailDto.releaseDate,
            trailerKey = trailer?.key
        )
    }
}
