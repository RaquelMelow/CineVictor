package com.example.cinevictor.presentation.features.reviews.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinevictor.data.repository.ReviewRepository
import com.example.cinevictor.presentation.features.reviews.viewModel.ReviewsViewModel
import com.example.cinevictor.presentation.ui.theme.CineVictorTheme


@Composable
fun ReviewsScreen(
    viewModel: ReviewsViewModel,
    modifier: Modifier = Modifier,
) {
    val friendsReviews by viewModel.friendsReviews.collectAsState()
    val popularReviews by viewModel.popularReviews.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadReviews()
    }

    Box(
        modifier = modifier
            .background(Color(0xFF1A1C2D))
            .padding(16.dp)
    ) {

        Column {
            Text(
                text = "New from friends",
                color = Color.White,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )

            LazyColumn {
                items(friendsReviews) { review ->
                    ReviewItem(review)
                }
            }
        }

        Text(
            text = "Popular This Week",
            color = Color.White,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
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
    device = "spec:shape=Normal,width=1080,height=2400,unit=px,dpi=480",
    showSystemUi = true
)

@Composable
fun PreviewReviewsScreen() {
    CineVictorTheme {
        Surface(Modifier.fillMaxSize()) {
            ReviewsScreen(viewModel = ReviewsViewModel(ReviewRepository()))
        }
    }
}