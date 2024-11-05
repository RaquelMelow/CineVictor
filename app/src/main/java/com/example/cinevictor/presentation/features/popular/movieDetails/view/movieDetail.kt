package com.example.cinevictor.presentation.features.popular.movieDetails.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.cinevictor.R
import com.example.cinevictor.domain.model.MovieDetailsCredit
import com.example.cinevictor.domain.model.CastMember
import com.example.cinevictor.presentation.ui.theme.CineVictorTheme

@Composable
fun MovieDetail(movieDetailsCredit: MovieDetailsCredit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {

        AsyncImage(
            placeholder = painterResource(R.drawable.backdrop),
            model = movieDetailsCredit.backdropPath,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

       Row {
            Column {
                Text(
                    text = movieDetailsCredit.title,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )

                Text(
                    text = movieDetailsCredit.overview,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

           AsyncImage(
               placeholder = painterResource(R.drawable.poster),
               model = movieDetailsCredit.posterPath,
               contentDescription = null,
               modifier = Modifier
                   .size(150.dp)
                   .clip(RoundedCornerShape(8.dp))
                   .padding(top = 20.dp)
           )

       }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Rating: ${movieDetailsCredit.voteAverage}/10",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Release Date: ${movieDetailsCredit.releaseDate}",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieDetail() {
    // Crea un objeto de ejemplo para la vista previa
    val sampleMovie = MovieDetailsCredit(
        id = 1,
        title = "Sample Movie Title",
        backdropPath = "https://image.tmdb.org/t/p/w500/gMQibswELoKmB60imE7WFMlCuqY.jpg",
        posterPath = "https://image.tmdb.org/t/p/w500/4xIzrMcEvCzJm5qAl92WMHLSIeM.jpg",
        overview = "This is a sample movie overview.",
        releaseDate = "2023-01-01",  // Asegúrate de incluir este parámetro aquí
        voteAverage = 7.5,
        voteCount = 100,
        runtime = 120,
        genres = listOf("Action", "Adventure"),
        cast = listOf(
            CastMember(id = 1, name = "Sample Cast Member", character = "Protagonist")
        )
    )
    MovieDetail(movieDetailsCredit = sampleMovie)
}


