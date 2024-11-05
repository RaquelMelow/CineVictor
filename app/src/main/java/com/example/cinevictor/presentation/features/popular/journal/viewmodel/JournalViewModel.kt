package com.example.cinevictor.presentation.features.popular.journal.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cinevictor.R
import com.example.cinevictor.presentation.features.popular.journal.model.JournalListsItemData
import com.example.cinevictor.presentation.features.popular.journal.model.sceneList1
import com.example.cinevictor.presentation.features.popular.lists.model.ListsItemData
import com.example.cinevictor.presentation.features.popular.lists.model.imageList1
import com.example.cinevictor.presentation.features.popular.lists.model.imageList2
import com.example.cinevictor.presentation.features.popular.lists.model.imageList3
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JournalViewModel : ViewModel() {

    private val _listsItems = MutableStateFlow<List<JournalListsItemData>>(emptyList())
    val sceneListsItems: StateFlow<List<JournalListsItemData>> = _listsItems

    //Elementos fijos pero flotantes que el view model toma para mostrar en el ListItemData.
    // Próximamente estos datos serán indexados a partir de la respuesta que nos de la API de TMDB
    init {
        _listsItems.value = listOf(
            JournalListsItemData(
                title = "Torrententontero",
                abstract = "Apatrullando la ciudad voy y vengo y sacando algunas perrillas al viejo",
                scene = R.drawable.torrente
            ),
            JournalListsItemData(
                title = "Un anillo para gobernarlos a todos?",
                abstract = "¡No flipes Beyonce! ¡A uno y no más! ¡Y de uno en uno siempre! ",
                scene = R.drawable.thering
            ),
            JournalListsItemData(
                title = "Con uno así...",
                abstract = "...quién me pitaria en la carretera o el centro de S/C?",
                scene = R.drawable.transformersone
            ),
            JournalListsItemData(
                title = "Ahora centro de menores con medidas judiciales",
                abstract = "Y tambien estan las Aldeas Infantiles",
                scene = R.drawable.elorfanato
            ),
            JournalListsItemData(
                title = "¡Qué tio este Gru!",
                abstract = "Sin duda sabe mucho de maldades, pero no ha sabido como escapar de ella",
                scene = R.drawable.gru4
            ),
            JournalListsItemData(
                title = "¡El es un pirata!",
                abstract = "¡Tantan tata tantan, tata tantan!",
                scene = R.drawable.flag
            )
        )
    }
}