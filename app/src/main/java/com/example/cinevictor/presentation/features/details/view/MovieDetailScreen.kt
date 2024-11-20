package com.example.cinevictor.presentation.features.details.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import coil.compose.AsyncImage
import com.example.cinevictor.R
import com.example.cinevictor.domain.model.CastMember
import com.example.cinevictor.domain.model.MovieDetailsCredit
import com.example.cinevictor.presentation.features.details.viewmodel.MovieDetailViewModel
import com.example.cinevictor.presentation.ui.theme.CineTemita
import com.example.cinevictor.presentation.ui.util.convertStringToDate
import com.example.cinevictor.presentation.ui.util.getYearFromDate
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailScreen(
    movieId: Int,
    viewModel: MovieDetailViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.loadMovieDetails(movieId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        state.error?.let {
            Text(
                text = it,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        state.movieDetails?.let { movieDetails ->
            Box {
                AsyncImage(
                    model = movieDetails.backdropPath,
                    placeholder = painterResource(R.drawable.backdrop),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(235.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.7f),
                                    Color.Black
                                )
                            )
                        )
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Row {
                    Column {
                        Text(
                            text = movieDetails.title,
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                        )

                        Text(
                            text = "Director by",
                            style = MaterialTheme.typography.labelLarge
                        )

                        Text(
                            text = movieDetails.directorName ?: "Unknown",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Row {
                            val releaseYear =
                                getYearFromDate(convertStringToDate(movieDetails.releaseDate))

                            Text(
                                text = releaseYear,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            )

                            Spacer(modifier = Modifier.padding(3.dp))

                            Text(
                                text = "${movieDetails.runtime} min",
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                            )

                        }

                        Spacer(modifier = Modifier.padding(3.dp))


                    }

                    AsyncImage(
                        placeholder = painterResource(R.drawable.poster),
                        model = movieDetails.posterPath,
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )

                }

                Text(
                    text = movieDetails.overview,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                )

                HorizontalDivider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Rating",
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                        )


                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            MovieRating(rating = movieDetails.voteAverage.toFloat())
                        }
                    }
                }

                HorizontalDivider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                if (movieDetails.cast.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Cast",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    LazyRow(
                        modifier = Modifier.padding(top = 8.dp)
                    ) {

                        items(movieDetails.cast) { castMember ->
                            CastMemberItem(castMember)
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun MovieRating(rating: Float) {
    val progress = rating / 10f

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        /*Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Star",
            tint = Color.Green,
            modifier = Modifier
                .size(16.dp)
        )
        */
        LinearProgressIndicator(
            progress = { progress },
            color = Color.Green,
            trackColor = Color.Gray.copy(alpha = 0.3f),
            strokeCap = StrokeCap.Butt,
            gapSize = 0.dp,
            drawStopIndicator = {},
            modifier = Modifier
                .weight(1f)
                .height(8.dp)

        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(start = 8.dp)
                .padding(top = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Star",
                        tint = Color.Green,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Text(
                text = String.format("%.1f", rating),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 50.dp)
            )
        }
    }
}

@Composable
fun CastMemberItem(castMember: CastMember) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = castMember.profilePath,
                placeholder = painterResource(R.drawable.perfilamanda),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = castMember.name,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = castMember.character,
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewMovieDetail() {
    val sampleMovie = MovieDetailsCredit(
        id = 1034541,
        title = "Sample Movie Title",
        backdropPath = "https://image.tmdb.org/t/p/w500/gMQibswELoKmB60imE7WFMlCuqY.jpg",
        posterPath = "https://image.tmdb.org/t/p/w500/4xIzrMcEvCzJm5qAl92WMHLSIeM.jpg",
        overview = "El hombre, desde su origen, guiado por unas miras que pretenden ser prácticas, ha ido enmendando la plana a la Naturaleza y convirtiéndola en campo. ",
        releaseDate = "2023-01-01",
        voteAverage = 7.5,
        voteCount = 100,
        runtime = 120,
        genres = listOf("Action", "Adventure"),
        cast = listOf(
            CastMember(id = 1, name = "Damien Leone", character = "Protagonist", job = "Director", profilePath = "https://image.tmdb.org/t/p/w500/9nYijs4ACzjg93zKezLiLmuRGvp.jpg"),
            CastMember(id = 2, name = "Sample Actor", character = "Sidekick", job = "Actor", profilePath = "https://image.tmdb.org/t/p/w500/qJYWq2oZcvHh7lnGskxkrYXCom0.jpg" )
        ),
    )
    CineTemita {
        //viewModel.loadMovieDetails(sampleMovie.id) // Cargar los detalles de la película en el ViewModel
        //MovieDetailScreen(movieId = sampleMovie.id, viewModel = viewModel)
    }
}


