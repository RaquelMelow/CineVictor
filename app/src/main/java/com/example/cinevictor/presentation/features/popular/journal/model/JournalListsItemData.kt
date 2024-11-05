package com.example.cinevictor.presentation.features.popular.journal.model

import android.transition.Scene
import androidx.annotation.DrawableRes
import com.example.cinevictor.R
import com.example.cinevictor.data.network.MovieService


val sceneList1 = listOf(
    R.drawable.torrente,
    R.drawable.thering,
    R.drawable.transformersone,
    R.drawable.elorfanato,
    R.drawable.gru4,
    R.drawable.flag,
    )


// Forma de jerarquización de la información que acompaña a cada
// carrusel de imágenes
data class JournalListsItemData(
    @DrawableRes val scene: Int,
    val title: String?,
    val abstract: String?,

)