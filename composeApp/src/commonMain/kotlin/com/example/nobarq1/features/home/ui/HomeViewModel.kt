package com.example.nobarq1.features.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nobarq1.features.home.data.MovieRepository
import com.example.nobarq1.features.home.domain.Genre
import com.example.nobarq1.features.home.domain.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class HomeState {
    object Loading : HomeState()
    data class Success(val categories: List<GenreMovies>) : HomeState()
    data class Error(val message: String) : HomeState()
}

data class GenreMovies(
    val genre: Genre,
    val movies: List<Movie>
)

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            try {
                _state.value = HomeState.Loading
                val genres = repository.getGenres().take(10)
                val categories = genres.map { genre ->
                    GenreMovies(genre, repository.getMoviesByGenre(genre.id))
                }
                _state.value = HomeState.Success(categories)
            } catch (e: Exception) {
                _state.value = HomeState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
