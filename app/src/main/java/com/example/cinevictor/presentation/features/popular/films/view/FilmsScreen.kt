package com.example.cinevictor.presentation.features.popular.films.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cinevictor.domain.model.Movie
import com.example.cinevictor.presentation.features.films.viewmodel.FilmsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun FilmsScreen(
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FilmsViewModel = koinViewModel()
) {

    val popularOfTheWeek by viewModel.popularOfTheWeek.collectAsState()
    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState) {
        snapshotFlow {
            gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }
            .collect { lastVisibleIndex ->
                if (lastVisibleIndex == popularOfTheWeek.size - 1) {
                    viewModel.loadMovies()
                }
            }
    }


    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
        state = gridState,
        modifier = modifier.fillMaxSize()
    ) {
        items(popularOfTheWeek) { movie ->
            MovieItem(movie, navigateToDetail)
        }
    }
}

@Composable
fun MovieItem(
    data: Movie,
    navigateToDetail: (Int) -> Unit,
) {
    val imageUrl = "https://image.tmdb.org/t/p/w500${data.posterPath}"
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(150.dp)
            .padding(2.dp)
            .clip(RoundedCornerShape(5.dp))
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,

            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navigateToDetail(data.id)
                }
        )

    }
}

@Preview(showBackground = true, device = Devices.PIXEL_6, showSystemUi = true)
@Composable
fun PreviewFilms() {
    Surface(Modifier.fillMaxWidth()) {
        FilmsScreen(navigateToDetail = {

        })
    }
}
