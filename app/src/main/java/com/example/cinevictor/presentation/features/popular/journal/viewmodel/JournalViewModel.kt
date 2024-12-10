package com.example.cinevictor.presentation.features.popular.journal.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.core.framework.network.retrofit.MovieService
import com.example.cinevictor.data.local.dao.MovieDao
import com.example.cinevictor.data.repository.MovieRepository
import com.example.cinevictor.domain.model.Movie
import com.example.cinevictor.domain.util.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class A

class MyViewModel(a: A): ViewModel()

class JournalViewModel(
    private val service: MovieService,
    private val movieDao: MovieDao
) : ViewModel() {

    private val repository = MovieRepository(service, movieDao)

    private val _listsItems = MutableStateFlow<List<Movie>?>(null)
    val sceneListsItems: StateFlow<List<Movie>?> = _listsItems

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadUpcomingMovies()
    }

    private fun loadUpcomingMovies() {
        if (_isLoading.value) return

        _isLoading.value = true
        viewModelScope.launch {
            when (val result = repository.getUpcomingMovies(1)) {
                is ApiResult.Success -> {
                    _listsItems.value = result.data
                    _isLoading.value = false
                }
                is ApiResult.Error -> {
                    _isLoading.value = false
                }
            }
        }

    }
}
