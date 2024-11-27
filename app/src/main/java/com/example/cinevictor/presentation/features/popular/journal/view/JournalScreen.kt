package com.example.cinevictor.presentation.features.popular.journal.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cinevictor.domain.model.Movie
import com.example.cinevictor.presentation.features.popular.journal.viewmodel.JournalViewModel

// Construccion de la columna

@Composable
fun JournalScreen(
    state: LazyListState,
    movies: List<Movie>,
    selectedMovie: Movie?,
    onClick: (movie: Movie) -> Unit,
    onDismiss: () -> Unit
) {
    /*    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Simula un tiempo de carga
        kotlinx.coroutines.delay(2000)
        isLoading = false
    }

    if (isLoading) {
        // Mostrar indicador de carga
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    } else {*/

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
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
                AsyncImage(
                    model = movie.posterPath,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .size(200.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                )

                Text(
                    text = movie.title,
                    textAlign = TextAlign.Left,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Normal
                )

                Text(
                    text = movie.overview,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Normal,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = {
                            onClick(movie)
                        })
                )

                Spacer(Modifier.height(15.dp))

            }
        }
    }

    selectedMovie?.let {
            // Ventana modal, se pica sobre el texto que acaba en puntos suspensivos y
            //  se abre una ventana que expone el texto/sinpsis completo
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
                        shape = RoundedCornerShape(4.dp), // Para dar forma al botón
                        border = BorderStroke(1.dp, Color.White) // Para añadir el borde blanco/rojo
                                            )
                    { Text("Close", color = Color.White) }
                },
                containerColor = Color.Black, // Color de fondo del dialogo
                shape = RoundedCornerShape(8.dp),

            )
        }
}
//}


@PreviewScreenSizes
@Composable
fun PreviewJournalScreen() {
    Surface {
        //JournalScreen()
    }
}