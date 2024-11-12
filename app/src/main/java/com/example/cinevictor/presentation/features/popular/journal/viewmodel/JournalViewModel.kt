package com.example.cinevictor.presentation.features.popular.journal.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.data.network.MovieService
import com.example.cinevictor.data.network.RetrofitClient
import com.example.cinevictor.data.repository.MovieRepository
import com.example.cinevictor.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JournalViewModel : ViewModel() {

    private val movieService = RetrofitClient.retrofit.create(MovieService::class.java)
    private val repository = MovieRepository(movieService)

    private val _listsItems = MutableStateFlow<List<Movie>?>(emptyList())
    val sceneListsItems: StateFlow<List<Movie>?> = _listsItems

    init {
        viewModelScope.launch {
            val movies: List<Movie>? = repository.getUpcomingMovies(1)
            _listsItems.value = movies
        }
    }
}