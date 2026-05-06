package com.example.nobarq1.features.liked.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nobarq1.features.home.data.MovieRepository
import com.example.nobarq1.features.home.domain.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LikedViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    fun loadLikedMovies() {
        viewModelScope.launch {
            _movies.value = repository.getFavorites()
        }
    }
}
