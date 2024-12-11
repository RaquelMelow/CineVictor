package com.example.cinevictor.presentation.features.popular.lists.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinevictor.R
import com.example.cinevictor.presentation.features.popular.lists.model.ListsItemData
import com.example.cinevictor.presentation.features.popular.lists.model.imageList1
import com.example.cinevictor.presentation.features.popular.lists.model.imageList2
import com.example.cinevictor.presentation.features.popular.lists.model.imageList3
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListsViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _listsItems = MutableStateFlow<List<ListsItemData>>(emptyList())
    val listsItems: StateFlow<List<ListsItemData>> = _listsItems

    init {
        loadListsData()
    }

    fun loadListsData() {
        if (_isLoading.value) return

        viewModelScope.launch {
            try {
                _isLoading.value = true
                delay(1000)
                _listsItems.value = createSampleData()
            } catch (e: Exception) {
            } finally {
                _isLoading.value = false
            }
        }
    }


    private fun createSampleData(): List<ListsItemData> {
        return listOf(
            ListsItemData(
                title = "Films directed by women",
                movieImages = imageList1,
                concept = "Check out the official top narrative films by women directors list",
                abstract = "Female directors are still underrepresented in the film industry and to this list is a place to highlight their work.",
                friend = "Vanessa",
                image = R.drawable.vanesamartin,
            ),
            ListsItemData(
                title = "Troperías de Abby",
                movieImages = imageList2,
                concept = null,
                abstract = "Me encanta el olor a gasolina por la mañana. ¡Huele a victoria!",
                friend = "Abby",
                image = R.drawable.abby
            ),
            ListsItemData(
                title = "Troperías de Buff",
                movieImages = imageList3,
                concept = null,
                friend = "Buff",
                abstract = "Soy un tío de gustos sencillos. Me gusta la dinamita, la pólvora y la gasolina! ¿Y sabes qué tienen en común? ¡Que son baratas!",
                image = R.drawable.flag
            ),
            ListsItemData(
                title = "Troperías de Iru",
                movieImages = imageList2,
                concept = null,
                abstract = "Soy un tío de gustos sencillos. Me gusta la dinamita, la pólvora y la gasoliiiiiina!",
                friend = "Iru",
                image = R.drawable.flag
            )
        )
    }
}
