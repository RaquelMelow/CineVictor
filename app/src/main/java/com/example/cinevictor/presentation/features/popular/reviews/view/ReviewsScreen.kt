package com.example.cinevictor.presentation.features.reviews.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cinevictor.presentation.features.popular.reviews.view.ReviewItem
import com.example.cinevictor.presentation.features.popular.reviews.viewModel.ReviewsViewModel
import com.example.cinevictor.presentation.ui.theme.CineTemita


@Composable
fun ReviewsScreen(
    viewModel: ReviewsViewModel = viewModel()
) {
    val popularReviews by viewModel.popularReviews.collectAsState()


    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Text(
            text = "Popular This Week",
            style = MaterialTheme.typography.titleLarge
        )


        LazyColumn {
            items(popularReviews) { review ->
                ReviewItem(review)
            }
        }
    }
}


@Preview(
    name = "Pixel 5",
    device = "spec:width=1080dp,height=2400dp,dpi=480",
    showSystemUi = true
)

@Composable
fun PreviewReviewsScreen() {
    CineTemita {
        Surface(Modifier.fillMaxSize()) {
            ReviewsScreen()
        }
    }
}