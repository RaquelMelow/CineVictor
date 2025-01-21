package com.example.cinevictor.presentation.features.popular.journal.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cinevictor.domain.model.Movie

// Construccion de la columna

@Composable
fun JournalScreen(
    state: LazyListState,
    movies: List<Movie>,
    selectedMovie: Movie?,
    onClick: (movie: Movie) -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(10.dp),
            state = state
        ) {
            items(
                movies,
                key = { it.id }
            ) { movie ->
                // Card para la película con fondo gris oscuro
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF333333)), // Gris oscuro
                    shape = RectangleShape,  // Forma rectangular sin bordes redondeados
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .clickable { onClick(movie) } // Clickable para abrir detalles
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // Imagen de la película
                        AsyncImage(
                            model = movie.posterPath,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentDescription = null,
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Crop
                        )

                        // Título de la película
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = movie.title,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        // Sinopsis (overview) con puntos suspensivos
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = movie.overview,
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Normal,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3
                        )

                        // Información adicional (como autor, calificación, etc.) si es necesario
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Release Date: ${movie.releaseDate}",
                            color = Color.Gray,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }

    selectedMovie?.let {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = it.title, color = Color.White) },
            text = { Text(text = it.overview, color = Color.White) },
            confirmButton = {
                Button(
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black // Color de fondo del botón
                    ),
                    shape = RoundedCornerShape(4.dp), // Forma del botón
                    border = BorderStroke(1.dp, Color.White) // Borde del botón
                ) {
                    Text("Close", color = Color.White)
                }
            },
            containerColor = Color.Black, // Fondo del diálogo
            shape = RoundedCornerShape(8.dp)
        )
    }
}




