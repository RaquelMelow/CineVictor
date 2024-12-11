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
import com.example.cinevictor.presentation.ui.components.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun JournalRoute(
    viewModel: JournalViewModel = koinViewModel()
) {
    val uiState by viewModel.sceneListsItems.collectAsState()
    var selectedMovie by remember { mutableStateOf<Movie?>(null) }
    val state = rememberLazyListState()
    val isLoading by viewModel.isLoading.collectAsState()


    if(isLoading) {
        LoadingScreen()
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
//        is UiState.Loading -> {Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            CircularProgressIndicator()
//          }
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