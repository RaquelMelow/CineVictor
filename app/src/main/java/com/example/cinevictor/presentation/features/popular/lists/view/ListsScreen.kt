package com.example.cinevictor.presentation.features.popular.lists.view

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cinevictor.presentation.features.popular.lists.model.ListsItemData
import com.example.cinevictor.presentation.features.popular.lists.viewmodel.ListsViewModel
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random


@Composable
fun ListsScreen(
    viewModel: ListsViewModel = koinViewModel()
) {

    val items by viewModel.listsItems.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "New from friends",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items) { item ->
                ListsItem(item)
            }
        }
    }
}

@Composable
fun ListsItem(data: ListsItemData) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF333333)),
        shape = RectangleShape,
        modifier = Modifier.padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                data.title?.let {
                    Text(
                        text = it,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                Spacer(Modifier.weight(1f))

                Text(
                    text = data.friend,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic
                )

                Image(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(40.dp),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(data.image),
                    contentDescription = null
                )
            }

            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                items(data.movieImages) { imageRes ->
                    MovieRowItem(imageRes)
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = data.concept ?: "",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            data.abstract?.let {
                Text(
                    text = data.abstract,
                    color = Color.Gray,  
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp
                )
            }
        }
    }
}

val borderColor = Color(
    red = Random.nextFloat(),
    green = Random.nextFloat(),
    blue = Random.nextFloat(),
    alpha = 1f
)

@Composable
fun MovieRowItem(posterPath: String) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier
            .border(
                width = 4.dp,
                color = borderColor,
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        AsyncImage(
            model = posterPath,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(120.dp)
                .padding(2.dp)
                .clip(RoundedCornerShape(2.dp))
        )
    }
}





