package com.example.cinevictor.presentation.features.popular.journal.view

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.cinevictor.domain.model.Movie
import com.example.cinevictor.presentation.features.popular.journal.viewmodel.JournalViewModel
import com.example.cinevictor.presentation.features.popular.journal.viewmodel.UiState

@Composable
fun JournalRoute(
    viewModel: JournalViewModel
) {
    val uiState by viewModel.sceneListsItems.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var selectedMovie by remember { mutableStateOf<Movie?>(null) }
    val state = rememberLazyListState()

    if(isLoading) {
        CircularProgressIndicator()
    }

    uiState?.let {
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


//    when(val uiState = uiState) {
//        is UiState.Loading -> {
//            CircularProgressIndicator()
//        }
//
//        is UiState.Success -> {
//            uiState.movies?.let {
//                JournalScreen(
//                    state = state,
//                    movies = it,
//                    selectedMovie = selectedMovie,
//                    onClick = { movie ->
//                        selectedMovie = movie
//                    },
//                    onDismiss = {
//                        selectedMovie = null
//                    }
//                )
//            }
//        }
//    }


}