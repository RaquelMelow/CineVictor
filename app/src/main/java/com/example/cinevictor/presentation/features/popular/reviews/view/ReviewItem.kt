package com.example.cinevictor.presentation.features.popular.reviews.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cinevictor.data.local.database.ReviewWithMovie

@Composable
fun ReviewItem(review: ReviewWithMovie) {
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        modifier = Modifier.padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = review.movieTitle,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = review.releaseDate,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Autor y avatar
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                review.authorAvatar?.let {
                    AsyncImage(
                        model = it,
                        contentDescription = "Author Avatar",
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.Gray, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = review.author.ifBlank { "Unknown" },
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    )

                Spacer(modifier = Modifier.weight(1f))

                review.rating?.let {
                    Text(
                        text = "⭐ ${it}/10",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Green
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Reseña y póster
            Row {
                AsyncImage(
                    model = review.moviePosterUrl,
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .size(70.dp, 100.dp)
                        .border(1.dp, MaterialTheme.colorScheme.onSurfaceVariant),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = review.content,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider(
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

