package com.example.cinevictor.presentation.features.popular.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.data.repository.MovieRepository
import com.example.cinevictor.domain.model.MovieDetailsCredit
import com.example.cinevictor.domain.util.ApiResult
import com.example.cinevictor.domain.util.toMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MovieDetailState(
    val movieDetails: MovieDetailsCredit? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailState())
    val state: StateFlow<MovieDetailState> = _state


    fun loadMovieDetails(id: Int) {
        viewModelScope.launch {

            _state.update {state.value.copy(isLoading = true) }

            when (val response = repository.getMovieDetails(id)) {
                is ApiResult.Error -> {
                    _state.update {
                        delay(2000)
                        state.value.copy(
                            isLoading = false,
                            error = response.error.toMessage())

                    }
                }

                is ApiResult.Success -> {
                    _state.update {
                        state.value.copy(
                            isLoading = false,
                            movieDetails = response.data,
                            error = null
                        )
                    }
                }
            }
        }
    }
}
