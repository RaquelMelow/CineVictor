package com.example.cinevictor.presentation.features.reviews.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinevictor.R
import com.example.cinevictor.data.local.MoviesReviewStore
import com.example.cinevictor.presentation.features.reviews.model.ReviewData
import com.example.cinevictor.model.User

val reviewsFriends = listOf(
    ReviewData(
        id = "1",
        user = User(id = "u0", name = "Amanda", R.drawable.perfilamanda),
        movie = MoviesReviewStore.popularWithFriends[0],
        rating = 4,
        comment = "Flojita",
        date = "2024-09-29"
    )
)

val reviewData = listOf(
    ReviewData(
        id = "1",
        user = User(id = "u1", name = "Juan", R.drawable.perfiljuan),
        movie = MoviesReviewStore.popularThisWeek[0],
        rating = 8,
        comment = "Excelente película",
        date = "2024-09-29"
    ),
    ReviewData(
        id = "2",
        user = User(id = "u2", name = "María", R.drawable.perfilmaria),
        movie = MoviesReviewStore.popularThisWeek[1],
        rating = 7,
        comment = "Muy entretenida",
        date = "2024-09-28"
    )
)

@Composable
fun ReviewsScreen(
    modifier: Modifier = Modifier,
) {
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
                items(reviewsFriends) { review ->
                    ReviewItem(review)
                }
            }

            Text(
                text = "Popular This Week",
                color = Color.White,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
            LazyColumn {
                items(reviewData) { review ->
                    ReviewItem(review)
                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    device = Devices.PIXEL_4,
    widthDp = 360,
    heightDp = 640,
)
@Composable
fun PreviewReviewsScreen() {
    Column(Modifier.fillMaxSize()) {
        ReviewsScreen()
    }
}