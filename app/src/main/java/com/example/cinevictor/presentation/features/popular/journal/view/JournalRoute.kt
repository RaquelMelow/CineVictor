package com.example.cinevictor.presentation.features.popular.journal.view

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.cinevictor.domain.model.Movie
import com.example.cinevictor.presentation.features.popular.journal.viewmodel.JournalViewModel

@Composable
fun JournalRoute(
    viewModel: JournalViewModel = JournalViewModel()
) {
    val movies by viewModel.sceneListsItems.collectAsState()
    var selectedMovie by remember { mutableStateOf<Movie?>(null) }
    val state = rememberLazyListState()

    movies?.let {
        JournalScreen(
            state = state,
            movies = it,
            selectedMovie = selectedMovie,
            onClick = { movie ->
                selectedMovie = movie
            },
            onDismiss = {
                selectedMovie = null
            }
        )
    }
}