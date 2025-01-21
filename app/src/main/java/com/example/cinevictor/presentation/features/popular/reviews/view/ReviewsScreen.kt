package com.example.cinevictor.presentation.features.reviews.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cinevictor.presentation.features.popular.reviews.view.ReviewItem
import com.example.cinevictor.presentation.features.popular.reviews.viewModel.ReviewsViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel


@Composable
fun ReviewsScreen(viewModel: ReviewsViewModel = koinViewModel()) {
    val reviews by viewModel.reviewsByMovie.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadReviews()
    }

    Column {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(horizontal = 100.dp, vertical = 100.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(80.dp)
            )
        } else {
            LazyColumn {
                items(reviews) { review ->
                    ReviewItem(review)
                }
            }
        }
    }

}
