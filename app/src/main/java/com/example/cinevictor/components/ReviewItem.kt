package com.example.reviewcinevictor.ui.reviews.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import model.Movie
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import model.Review


@Composable
fun ReviewItem(review: Review) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RectangleShape,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                Modifier.background(Color.Green),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "${review.movie.title}",
                    style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    )
                )

                Text(text = "${review.movie.year}",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light,
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(text = "${review.user.name}")
            }

            Text("${review.rating}/10",
                modifier = Modifier.padding(5.dp))

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.background(Color.Red)
            ) {
                Image(
                    painter = painterResource(id = review.movie.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(70.dp)
                        .aspectRatio(2f / 3f)
                        .clip(RoundedCornerShape(4.dp))
                        .padding(start = 0.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                        .border(
                            width = 1.dp,
                            color = Color.White
                        )
                )

                Text(
                        text = "${review.comment}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light
                        ),
                    )
            }
        }
    }
}

