package com.example.cinevictor.presentation.features.popular.lists.model

import androidx.annotation.DrawableRes
import com.example.cinevictor.R


// Forma de jerarquización de la información que acompaña a cada
// carrusel de imágenes
data class ListsItemData(
    val title: String?,
    val abstract: String?,
    val movieImages: List<Int>,
    val concept: String?,
    val friend: String,
    @DrawableRes val image: Int
)

// Carrusel de imágenes
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