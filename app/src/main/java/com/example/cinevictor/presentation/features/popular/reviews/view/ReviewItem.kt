package com.example.cinevictor.presentation.features.popular.reviews.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cinevictor.presentation.features.popular.reviews.model.ReviewData

@Composable
fun ReviewItem(reviewData: ReviewData) {
    Card(
       colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.padding(top = 10.dp)) {

            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = reviewData.movie.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "${reviewData.movie.year}",
                    style = MaterialTheme.typography.bodyLarge
                    )


                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = reviewData.user.name,
                    modifier = Modifier.padding(top = 2.dp),
                    style = MaterialTheme.typography.bodyLarge
                    )


                Image(
                    painter = painterResource(id = reviewData.user.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .size(width = 20.dp, height = 20.dp)
                        .border(1.dp, Color(0xFFAFB3D0), CircleShape)
                        .clip(CircleShape)
                )
            }

            Text(
                "${reviewData.rating}/10",
                modifier = Modifier
                    .padding(2.dp),
                style = MaterialTheme.typography.labelSmall

            )


            Row {
                Image(
                    painter = painterResource(id = reviewData.movie.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 70.dp, height = 100.dp)
                        .border(1.dp, Color.White)
                )


                Text(
                    text = reviewData.comment,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .align(Alignment.Top)
                        .padding(start = 2.dp),
                )


            }

            HorizontalDivider(
               // color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

