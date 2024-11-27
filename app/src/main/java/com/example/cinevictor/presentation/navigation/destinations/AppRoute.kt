package com.example.cinevictor.presentation.navigation.destinations

import kotlinx.serialization.Serializable

sealed class AppRoute {
    @Serializable
    data object Popular: AppRoute()

    @Serializable
    data class Detail(val id: Int): AppRoute()

    @Serializable
    object Login : AppRoute()

    @Serializable
    object Register : AppRoute()
}