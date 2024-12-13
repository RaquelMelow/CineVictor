package com.example.cinevictor.presentation.features.reviews.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.cinevictor.presentation.features.popular.reviews.view.ReviewItem
import com.example.cinevictor.presentation.features.popular.reviews.viewModel.ReviewsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun ReviewsScreen(viewModel: ReviewsViewModel = koinViewModel()) {
    val reviews by viewModel.reviewsByMovie.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadReviews()
    }

    LazyColumn {
        items(reviews) { review ->
            ReviewItem(review)
        }
    }
}
