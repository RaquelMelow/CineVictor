package com.example.cinevictor.presentation.features.popular.lists.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.R
import com.example.cinevictor.data.repository.MovieRepository
import com.example.cinevictor.presentation.features.popular.lists.model.ListsItemData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListsViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _listsItems = MutableStateFlow<List<ListsItemData>>(emptyList())
    val listsItems: StateFlow<List<ListsItemData>> = _listsItems

    // Elementos fijos pero flotantes que el view model toma para mostrar en el ListItemData.
    // Próximamente estos datos serán index
    // ados a partir de la respuesta que nos de la API de TMDB
    init {

        viewModelScope.launch {
            val dramaList = repository.getPostersByGenre(18)
            val actionList = repository.getPostersByGenre(28)
            val animationList = repository.getPostersByGenre(16)
            val scyfiList = repository.getPostersByGenre(878)
            val animationnScyfiList = animationList + scyfiList
            val horrorList = repository.getPostersByGenre(27)

            _listsItems.value = listOf(
                ListsItemData(
                    title = "Films directed by women",
                    movieImages = dramaList,
                    concept = "Check out the official top narrative films by women directors list: DRAMA",
                    abstract = "Female directors are still underrepresented in the film industry and to this list is a place to highlight their work.",
                    friend = "Vanessa",
                    image = R.drawable.vanesamartin,
                ),
                ListsItemData(
                    title = "Troperías de Abby",
                    movieImages = actionList,
                    concept ="ACTION",
                    abstract = "Me encanta el olor a gasolina por la mañana. ¡Huele a victoria!",
                    friend = "Abby",
                    image = R.drawable.abby
                ),
                ListsItemData(
                    title = "Troperías de Buff",
                    movieImages = animationnScyfiList,
                    concept = "ANIMATION & SCYFI",
                    friend = "Buff",
                    abstract = "Soy un tío de gustos sencillos. Me gusta la dinamita, la polvora y la gasoliiina! ¿Y sabes qué tienen en común? ¡Que son baratas!",
                    image = R.drawable.flag
                ),
                ListsItemData(
                    title = "Troperías de Iru",
                    movieImages = horrorList,
                    concept = "HORROR",
                    abstract = "Soy un tío de gustos sencillos- Me gusta la dinamita, la pólvora y la gasoliiiiiina!",
                    friend = "Abby",
                    image = R.drawable.flag
                )
            )
        }
    }
}