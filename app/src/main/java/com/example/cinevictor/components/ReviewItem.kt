package com.example.reviewcinevictor.ui.reviews.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Review


@Composable
fun ReviewItem(review: Review) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1C2D),
            contentColor = Color.White
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${review.movie.title}",
                    style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold)
                )

                Text(text = "${review.movie.year}",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(text = "${review.user.name}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                    )
                )
            }

            Text("${review.rating}/10",
                modifier = Modifier
                    .padding(5.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row{
                Image(
                    painter = painterResource(id = review.movie.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 70.dp, height = 100.dp)
                        .border(1.dp, Color.White)
                )


                Text(
                    text = "${review.comment}",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                    ),
                    modifier = Modifier
                        .align(Alignment.Top)
                        .padding(start = 2.dp),
                    )


            }

            HorizontalDivider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

