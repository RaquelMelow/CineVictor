package com.example.cinevictor.presentation.features.films.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.R
import com.example.cinevictor.presentation.features.films.view.MovieData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class Foo()

class MoviesViewModel() : ViewModel() {

    private val _popularOfTheWeek = MutableStateFlow<List<MovieData>>(emptyList())
    val popularOfTheWeek: StateFlow<List<MovieData>> = _popularOfTheWeek.asStateFlow()

    private val _newForFriend = MutableStateFlow<List<MovieData>>(emptyList())
    val newForFriend: StateFlow<List<MovieData>> = _newForFriend.asStateFlow()

    private val _popularWithFriend = MutableStateFlow<List<MovieData>>(emptyList())
    val popularWithFriend: StateFlow<List<MovieData>> = _popularWithFriend.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch(Dispatchers.IO){

            _popularOfTheWeek.value = listOf(
                MovieData("Popular of the week", listOf(R.drawable.torrente,
                    R.drawable.eljoker,
                    R.drawable.avatar,
                    R.drawable.bleraner,
                    R.drawable.pulpfiction))
            )
            _newForFriend.value = listOf(
                MovieData("New for friend", listOf(R.drawable.avatar,
                    R.drawable.eljoker,
                    R.drawable.avatar,
                    R.drawable.bleraner,
                    R.drawable.pulpfiction))
            )
            _popularWithFriend.value = listOf(
                MovieData("Popular with friend", listOf(R.drawable.torrente,
                    R.drawable.eljoker,
                    R.drawable.avatar,
                    R.drawable.bleraner,
                    R.drawable.pulpfiction))
            )

        }
        /*val popularOfTheWeek = listOf<MovieData>(
            MovieData(
                "Popular of the week",
                listOf(
                    R.drawable.torrente,
                    R.drawable.eljoker,
                    R.drawable.avatar,
                    R.drawable.bleraner,
                    R.drawable.pulpfiction
                )
            ),
        )
        val newForFriend = listOf<MovieData>(
            MovieData(
                "New for friend",
                listOf(
                    R.drawable.avatar,
                    R.drawable.eljoker,
                    R.drawable.avatar,
                    R.drawable.bleraner,
                    R.drawable.pulpfiction
                )
            ),
        )
        val popularWithFriend = listOf<MovieData>(
            MovieData(
                "Popular whit friend",
                listOf(
                    R.drawable.torrente,
                    R.drawable.eljoker,
                    R.drawable.avatar,
                    R.drawable.bleraner,
                    R.drawable.pulpfiction
                )
            )
        )*/

    }
}