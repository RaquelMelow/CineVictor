package com.example.cinevictor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinevictor.ui.theme.CineVictorTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineVictorTheme {
                MovieListScreen()
                    }
                }
            }
        }

@Composable
fun MovieListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )

    {
        MovieItem(
            image = painterResource(id = R.drawable.movie1),
            title = "Título de Película 1",
            description = "Descripción breve de la película 1."
        )
        Spacer(modifier = Modifier.height(16.dp))
        MovieItem(
            image = painterResource(id = R.drawable.movie2),
            title = "Título de Película 2",
            description = "Descripción breve de la película 2."
        )
        Spacer(modifier = Modifier.height(16.dp))
        MovieItem(
            image = painterResource(id = R.drawable.movie3),
            title = "Título de Película 3",
            description = "Descripción breve de la película 3."
        )
    }
}

@Composable fun MovieItem(image: Painter, title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .shadow(elevation = 6.dp),
        shape = RectangleShape
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = image,
                contentDescription = "$title poster",
                modifier = Modifier.size(width = 200.dp, height = 150.dp)
            )
            Text(text = title, fontSize = 20.sp, modifier = Modifier.padding(top = 8.dp),
                color=Color.White)
            Text(text = description, fontSize = 14.sp, modifier = Modifier.padding(top = 4.dp),
                color=Color.White)
        }
    }
}

@Composable fun MovieItemd(image: Painter,title: String,description: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = image,
            contentDescription = "$title poster",
            modifier = Modifier.size(128.dp)
        )
        Text(text = title, fontSize = 20.sp, modifier = Modifier.padding(top = 8.dp))
        Text(text = description, fontSize = 14.sp, modifier = Modifier.padding(top = 4.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieList() {
    CineVictorTheme {
        MovieListScreen()
    }
}

