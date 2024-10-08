package com.example.reviewcinevictor.ui.reviews.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1C2D),
            contentColor = Color.White
        )
    ) {
        Column(modifier = Modifier.padding(top = 10.dp)) {

            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${review.movie.title}",
                    style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE4E7FF)
                    )
                )

                Text(text = "${review.movie.year}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFFE4E7FF)
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(text = "${review.user.name}",
                    modifier = Modifier.padding(top = 2.dp),
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFAFB3D0)
                    )
                )

                Image(
                    painter = painterResource(id = review.user.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .size(width = 20.dp, height = 20.dp)
                        .border(1.dp, Color(0xFFAFB3D0), CircleShape)
                        .clip(CircleShape)
                    )
            }

            Text("${review.rating}/10",
                modifier = Modifier
                    .padding(2.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = Color(0xFF00FF0A)
                )
            )


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

