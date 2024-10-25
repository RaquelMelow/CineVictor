package com.example.decinevictor000.presentation.features.lists.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cinevictor.R
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

val imageList1 = listOf(
    R.drawable.airplaneaterrizacomopuedas,
    R.drawable.amelie,
    R.drawable.batmanthedarkknight,
    R.drawable.crouchingtigerhiddendragon,
    R.drawable.diehardlajungla,
    R.drawable.disclosure,
    R.drawable.ferrisbuellersdayofftodoenundia,
    R.drawable.inception,
    R.drawable.johnwick,
    R.drawable.madmaxfuryroadfuriaenlacarretera
)

val imageList2 = listOf(
    R.drawable.thematrixresurrections02,
    R.drawable.theraidredemption,
    R.drawable.sense802,
    R.drawable.skyfall007,
    R.drawable.somelikeithot02,
    R.drawable.tangerine02,
    R.drawable.terminator2judgmentday02,
    R.drawable.oldboy02,
    R.drawable.johnwick02,
    R.drawable.thematrixresurrections
)

val imageList3 = listOf(
    R.drawable.montypythonandtheholygrail,
    R.drawable.oldboy,
    R.drawable.sense8,
    R.drawable.skyfall007,
    R.drawable.somelikeithot,
    R.drawable.tangerine,
    R.drawable.terminator2judgmentday,
    R.drawable.thebourneidentity,
    R.drawable.johnwick02,
    R.drawable.thematrixresurrections
)

@Composable
fun ListsScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {

        Text(
            text = "New from friends",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ListsItem(
                    ListsItemData(
                        title = "Films directed by women",
                        movieImages = imageList1,
                        concept = "Check out the official top narrative films by women directors list",
                        abstract = "Female directors are still underrepresented in the film industry and to this list is a place to highlight their work.",
                        friend = "Vanessa",
                        image = R.drawable.vanesamartin,
                        )
                )
            }

            item {
                ListsItem(
                    ListsItemData(
                        title = "Troperías de Abby",
                        movieImages = imageList2,
                        concept = null,
                        abstract = "Me encanta el olor a gasolina por la mañana. ¡Huele a victoria!",
                        friend = "Abby",
                        image = R.drawable.abby
                    )
                )
            }

            item {
                ListsItem(
                    ListsItemData(
                        title = "Troperías de Buff",
                        movieImages = imageList3,
                        concept = null,
                        friend = "Buff",
                        abstract = "Soy un tío de gustos sencillos. Me gusta la dinamita, la polvora y la gasoliiina! ¿Y sabes qué tienen en común? ¡Que son baratas!",
                        image = R.drawable.flag
                    )
                )
            }
        }
    }
}

@Composable
fun ListsItem(data: ListsItemData) {
    Column(
        modifier = Modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            data.title?.let {
                Text(
                    text = it,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Normal
                )
            }

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
                .padding(horizontal = 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(data.movieImages) { imageRes ->
                MovieRowItem(imageRes)
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = data.concept ?: "",
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        data.abstract?.let {
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
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .padding(10.dp)// Ajusta el tamaño según sea necesario
                .clip(RoundedCornerShape(5.dp))
        )
    }
}

data class ListsItemData(
    val title: String?,
    val abstract: String?,
    val movieImages: List<Int>,
    val concept: String?,
    val friend: String,
    @DrawableRes val image: Int
)


@Preview(
    name = "Pixel 5",
    device = "spec:shape=Normal,width=1080,height=2400,unit=px,dpi=480",
)
@Composable
fun PreviewListScreen() {
    Surface {
        ListsScreen()
    }
}






