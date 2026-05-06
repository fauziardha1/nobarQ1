package com.example.nobarq1.features.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nobarq1.features.home.data.MovieRepository
import com.example.nobarq1.features.home.domain.MovieDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailState {
    object Loading : DetailState()
    data class Success(val movie: MovieDetail) : DetailState()
    data class Error(val message: String) : DetailState()
}

class DetailViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow<DetailState>(DetailState.Loading)
    val state: StateFlow<DetailState> = _state.asStateFlow()

    fun loadMovieDetail(movieId: Int) {
        viewModelScope.launch {
            try {
                _state.value = DetailState.Loading
                val detail = repository.getMovieDetail(movieId)
                _state.value = DetailState.Success(detail)
            } catch (e: Exception) {
                _state.value = DetailState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
