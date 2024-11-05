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
import androidx.compose.material3.MaterialTheme
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

    val viewModel: FilmsViewModel = viewModel<FilmsViewModel>()

    val popularOfTheWeek by viewModel.popularOfTheWeek.collectAsState()
//    val newForFriend by viewModel.newForFriend.collectAsState(initial = emptyList())
//    val popularWithFriend by viewModel.popularWithFriend.collectAsState(initial = emptyList())

    LazyColumn(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        items(popularOfTheWeek) { movie ->
            MovieItem(movie)
        }
//        items(newForFriend) { movie ->
//            MovieItem(movie)
//        }
//        items(popularWithFriend) { movie ->
//            MovieItem(movie)
//        }

    }
}

@Composable
fun MovieItem(data: Movie) {
    val imageUrl = "https://image.tmdb.org/t/p/w500${data.posterPath}"
    Column {

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(100.dp)
                .height(150.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            text = data.movieTitle,
            color = Color.White,

            )

//        LazyRow {
//            items(data.posters) { resourceId ->
//                Image(
//                    painter = painterResource(resourceId),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .width(100.dp)
//                        .height(200.dp)
//                        .padding(
//                            top = 25.dp,
//                            bottom = 25.dp,
//                            end = 5.dp
//                        )
//                        .clip(RoundedCornerShape(10.dp))
//                )
//            }
//        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_6, showSystemUi = true)
@Composable
fun PreviewFilms() {
    Surface(Modifier.fillMaxWidth()) {
        FilmsScreen()
    }
}
