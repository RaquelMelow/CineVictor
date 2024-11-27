package com.example.cinevictor.presentation.features.films.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.cinevictor.domain.model.Movie
import com.example.cinevictor.presentation.features.films.viewmodel.FilmsViewModel


@Composable
fun FilmsScreen(modifier: Modifier = Modifier) {


    val viewModel: FilmsViewModel = viewModel()
    val popularOfTheWeek by viewModel.popularOfTheWeek.collectAsState()
    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState) {
        snapshotFlow {
            gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleIndex ->
                if (lastVisibleIndex == popularOfTheWeek.size - 1) {
                    viewModel.loadMovies()
                }
            }
    }


    LazyVerticalGrid (
        columns = GridCells.Adaptive(minSize = 100.dp),
        state = gridState,
        modifier = modifier.fillMaxSize()
    ) {
        items(popularOfTheWeek) { movie ->
            MovieItem(movie)
        }
    }
}

@Composable
fun MovieItem(data: Movie) {
    val imageUrl = "https://image.tmdb.org/t/p/w500${data.posterPath}"
    Box (
            modifier = Modifier
                .width(100.dp)
                .height(150.dp)
                .padding(2.dp)
                .clip(RoundedCornerShape(5.dp))
            ){

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

    }
}

@Preview(showBackground = true, device = Devices.PIXEL_6, showSystemUi = true)
@Composable
fun PreviewFilms() {
    Surface(Modifier.fillMaxWidth()) {
        FilmsScreen()
    }
}
