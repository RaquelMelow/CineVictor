package com.example.cinevictor.presentation.features.popular.journal.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.core.framework.network.retrofit.RetrofitClient
import com.example.cinevictor.data.network.MovieService
import com.example.cinevictor.data.repository.MovieRepository
import com.example.cinevictor.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

            movies?.let {
                _listsItems.value = movies
                _isLoading.value = false
            }
        }
    }
}