package com.example.cinevictor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.decinevictor000.presentation.features.lists.view.ListsScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ListsScreen()
            }
        }
    }
}



// 1. **Define tus clases de datos**:
data class Movie(
    val id: Int,
    val title: String,
    val genre: String,
    val year: Int,
    val country: String,
    val director: String

)

data class Serie(
    val id: Int,
    val title: String,
    val genre: String,
    val year: Int,
    val country: String,
    val director: String
)


// 2. **Crea una lista de tus objetos**:

val moviesAndSeries = listOf(
    Movie(
        1,
        "Inception",
        "Acción",
        2010,
        "EEUU",
        "Christopher Nolan, 40 años en 2010"
    ),
    Movie(
        2,
        "Inception",
        "Ciencia ficción",
        2010,
        "EEUU",
        "Christopher Nolan, 40 años en 2010"
    ),
    Movie(
        3,
        "Amélie",
        "Comedia",
        2001,
        "Francia",
        "Jean-Pierre Jeunet, 48 años en 2001"
    ),
    Movie(4,
        "Amélie",
        "Romance",
        2001,
        "Francia",
        "Jean-Pierre Jeunet, 48 años en 2001"
    ),
    Movie(
        5,
        "The Matrix Resurrections",
        "Ciencia ficción",
        2021,
        "EEUU",
        "Lana Wachowski, Mujer transgénero"
    ),
    Movie(
        6,
        "The Matrix Resurrections",
        "Acción", 2021,
        "EEUU",
        "Lana Wachowski, Mujer transgénero"
    ),
    Serie(
        1,
        "Sense8",
        "Ciencia ficción",
        2018,
        "EEUU",
        "Lana & Lilly Wachowski, Mujer transgénero"
    ),
    Serie(
        2,
        "Sense8",
        "Drama",
        2018,
        "EEUU",
        "Lana & Lilly Wachowski, Mujer transgénero"
    ),
    Movie(
        7,
        "Tangerine",
        "Drama",
        2015,
        "EEUU",
        "Sean Baker, Cisgénero"
    ),
    Movie(
        8,
        "Tangerine",
        "Comedia",
        2015,
        "EEUU",
        "Sean Baker, Cisgénero"
    ),
    Movie(
        9,
        "Disclosure",
        "Documental",
        2020,
        "EEUU",
        "Sean Baker, Hombre transgénero"
    ),
    Movie(
        10,
        "Die hard (La jungla)",
        "Acción",
        1988,
        "EEUU",
        "John Mctiernan"
    ),
    Movie(
        11,
        "Mad Max: Fury Road (Furia en la carretera)",
        "Acción",
        2015,
        "Australia",
        "George Miller"
    ),
    Movie(
        12,
        "Batman, The Dark Knight (El caballero oscuro)",
        "Acción",
        2008,
        "EEUU",
        "Christopher Nolan"
    ),
    Movie(
        13,
        "Batman, The Dark Knight (El caballero oscuro)",
        "Adaptación de cómic/manga",
        2008,
        "EEUU",
        "Christopher Nolan"
    ),
    Movie(
        14,
        "Crouching Tiger, Hidden Dragon (Tigre y Dragón)",
        "Acción",
        2000,
        "China, Hong Kong, Taiwán, EEUU",
        "Ang Lee"
    ),
    Movie(
        15,
        "Crouching Tiger, Hidden Dragon (Tigre y Dragón)",
        "Acción",
        2000,
        "China, Hong Kong, Taiwán, EEUU",
        "Ang Lee"
    ),
    Movie(
        16,
        "Crouching Tiger, Hidden Dragon (Tigre y Dragón)",
        "Artes marciales",
        2000,
        "China, Hong Kong, Taiwán, EEUU",
        "Ang Lee"
    ),
    Movie(
        17,
        "The Raid: Redemption (Redada asesina)",
        "Acción",
        2011,
        "Indonesia",
        "Gareth Evans"
    ),
    Movie(
        18,
        "The Raid: Redemption (Redada asesina)",
        "Policíaca",
        2011,
        "Indonesia",
        "Gareth Evans"
    ),
    Movie(19,
        "The Raid: Redemption (Redada asesina)",
        "Thriller",
        2011,
        "Indonesia",
        "Gareth Evans"
    ),
    Movie(
        20,
        "Terminator 2: Judgment Day (El día del juicio)",
        "Acción",
        1991,
        "EEUU",
        "James Cameron"
    ),
    Movie(
        21,
        "Terminator 2: Judgment Day (El día del juicio)",
        "Ciencia ficción",
        1991,
        "EEUU",
        "James Cameron"
    ),
    Movie(
        22,
        "Oldboy",
        "Acción",
        2003,
        "Corea del Sur",
        "Park Chan-wook"
    ),
    Movie(
        23,
        "Oldboy",
        "Adapatación de cómic/manga",
        2003,
        "Corea del Sur",
        "Park Chan-wook"
    ),
    Movie(
        24,
        "Oldboy",
        "Drama",
        2003,
        "Corea del Sur",
        "Park Chan-wook"
    ),
    Movie(
        25,"Skyfall (007)",
        "Acción",
        2012,
        "Reino Unido",
        "Sam Mendes"
    ),
    Movie(
        26,
        "Skyfall (007)",
        "Adaptación literaria",
        2012,
        "Reino Unido, EEUU",
        "Sam Mendes"
    ),
    Movie(
        27,
        "The Bourne Identity (La identidad de Bourne)",
        "Accident",
        2002,
        "EEUU, Alemania, República Checa",
        "Doug Liman"
    ),
    Movie(
        28,
        "John Wick",
        "Acción",
        2014,
        "EEUU",
        "Chad Stahelski"
    ),
    Movie(
        29,
        "Some Like It Hot (Con faldas y a lo loco)",
        "Comedia",
        1959,
        "EEUU",
        "Billy Wilder"
    ),
    Movie(
        30,
        "Monty Python and the Holy Grail (Los caballeros de la mesa cuadrada y sus locos seguidores)",
        "Comedia",
        1975,
        "Reino Unido",
        "Terry Gilliam, Terry Jones"
    ),
    Movie(
        31,
        "Airplane! (Aterriza como puedas)",
        "Comedia",
        1980,
        "EEUU",
        "Jim Abrahams, David Zucker, Jerry Zucker"
    ),
    Movie(
        32,
        "Ferris Bueller's Day Off (Todo en un día)",
        "Comedia",
        1986,
        "EEUU",
        "John Hughes"
    )
)

// 3. **Crea una función composable para mostrar cada elemento**:

@Composable
fun MovieItem(movie: Movie) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Text(text = movie.title, style = MaterialTheme.typography.titleSmall)
        Text(text = "${movie.genre} - ${movie.year}")
        Text(text = movie.director)
    }
}

@Composable
fun SerieItem(serie: Serie) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Text(text = serie.title, style = MaterialTheme.typography.titleSmall)
        Text(text = "${serie.genre} - ${serie.year}")
        Text(text = serie.director)
    }
}

// 4. **Implementa la `LazyColumn`**:
@Composable
fun MoviesAndSeriesList(moviesAndSeries: List<Any>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 20.dp)
    ) {
        items(moviesAndSeries) { item ->
            when (item) {
                is Movie -> MovieItem(movie = item)
                is Serie -> SerieItem(serie = item)
            }
        }
    }
}

@Composable
fun StatusBar() {
    Box(
        modifier = Modifier
            .width(390.dp)
            .height(47.dp)
            .clipToBounds()
    ) {
        // Aquí puedes añadir el contenido que necesites
    }
}




//5. **Llama a la función en tu actividad o fragmento**:
@Composable
fun MainScreen() {
    MoviesAndSeriesList(moviesAndSeries = moviesAndSeries)
}

/*class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationlListCineVictorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}*/

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}*/

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationlListCineVictorTheme {
        Greeting("Android")
    }
}*/
