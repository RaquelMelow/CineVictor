package com.example.cinevictor.presentation.features.popular.lists.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinevictor.presentation.features.popular.lists.model.ListsItemData
import com.example.cinevictor.presentation.features.popular.lists.viewmodel.ListsViewModel
import com.example.cinevictor.presentation.ui.components.LoadingScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun ListsScreen(viewModel: ListsViewModel = koinViewModel()) {
    val isLoading by viewModel.isLoading.collectAsState()
    val items by viewModel.listsItems.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadListsData()
    }

    if (isLoading) {
        LoadingScreen()
    } else {
        Column(
            modifier = Modifier
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
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(items) { item ->
                    ListsItem(item)
                }
            }
        }
    }
}

@Composable
fun ListsItem(data: ListsItemData) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = data.title.orEmpty(),
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = data.friend,
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
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
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(data.movieImages) { imageRes ->
                MovieRowItem(imageRes)
            }
        }

        Spacer(Modifier.height(8.dp))

        if (!data.concept.isNullOrEmpty()) {
            Text(
                text = data.concept,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        if (!data.abstract.isNullOrEmpty()) {
            Text(
                text = data.abstract,
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun MovieRowItem(imageRes: Int) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}



