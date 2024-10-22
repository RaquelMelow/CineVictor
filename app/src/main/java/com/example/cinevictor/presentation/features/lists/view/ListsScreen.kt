package com.example.decinevictor000.presentation.features.lists.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.cinevictor.R

@Composable
fun ListsScreen() {
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

    LazyColumn {
        item {
            ListRow(ListsItemData(
                listTitle = "Nuevas de mis amigos",
                concepto01 = "Mujeres directoras",
                amigo01 = "Vanessa",
                image01 = Image(
                    painterResource(R.drawable.vanesamartin),
                    contentDescription = null
                ),
                images = TODO()
            )
        }

        item {
            ListRow(ListsItemData(
                listTitle = "dfsdfsdf",
                images = imageList2,
                concepto01 = "Mujeres directoras",
                amigo01 = "Vanessa",
                image01 = Image (
                    painterResource(R.drawable.vanesamartin),
                    contentDescription = null

                )
            ))
        }

        item {
            ListRow(ListsItemData(
                listTitle = "dfsdfsdf",
                images = imageList2,
                concepto01 = "Mujeres directoras",
                amigo01 = "Vanessa",
                image01 = Image (
                    painterResource(R.drawable.vanesamartin),
                    contentDescription = null

                )
            ))
        }
    }
}

data class ListsItemData(
    val listTitle: String,
    val images: List<Int>,
    val concepto01: String,
    val amigo01: String,
    val image01: Unit
)

@Composable
fun ListRow(data: ListsItemData) {
    Column {

        Text(data.listTitle)

        LazyRow(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(data.images) { imageRes ->
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

                    Text("")
                }
            }
        }

        Text("Movie directed by women")
    }
}

@Composable
private fun MovieCard() {

}

@Composable
@PreviewScreenSizes
private fun ListScreenPreview() {
    ListsScreen()
}