package com.example.cinevictor.presentation.features.popular.journal.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.data.network.MovieService
import com.example.cinevictor.data.network.RetrofitClient
import com.example.cinevictor.data.repository.MovieRepository
import com.example.cinevictor.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Thread.State

sealed class UiState {
    data object Loading : UiState()
    data class Success(val movies: List<Movie>?) : UiState()
}

data class UiState2(
    val isLoading: Boolean = false,
    val movies: List<Movie>? = null
)

class JournalViewModel : ViewModel() {

    private val movieService = RetrofitClient.retrofit.create(MovieService::class.java)
    private val repository = MovieRepository(movieService)

    private val _listsItems = MutableStateFlow<List<Movie>?>(null)
    val sceneListsItems: StateFlow<List<Movie>?> = _listsItems

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        _isLoading.value = true
        viewModelScope.launch {

            val movies: List<Movie>? = repository.getUpcomingMovies(1)

            _listsItems.value = movies

            _isLoading.value = false
        }
    }
}