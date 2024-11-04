package com.example.cinevictor.data.local

import com.example.cinevictor.R
import com.example.cinevictor.domain.model.MovieLocal

object MoviesReviewStore {
    val popularThisWeek = listOf(
        MovieLocal(
            title = "Challenger",
            year = 2024,
            image = R.drawable.challenger
        ),
        MovieLocal(
            title = "Dune: Part II",
            year = 2023,
            image = R.drawable.dune2
        ),
        MovieLocal(
            title = "El Hoyo 2",
            year = 2024,
            image = R.drawable.elhoyo2
        ),
        MovieLocal(
            title = "Sherk",
            year = 2001,
            image = R.drawable.sherk
        ),
        MovieLocal(
            title = "Midsommar",
            year = 2019,
            image = R.drawable.midsommar
        )
    )
    val newFromFriends = listOf(
        MovieLocal(
            title = "Smile 2",
            year = 2024,
            image = R.drawable.smile2
        ),
        MovieLocal(
            title = "The Ring",
            year = 1999,
            image = R.drawable.thering
        ),
        MovieLocal(
            title = "As Bestas",
            year = 2022,
            image = R.drawable.asbestas
        ),
        MovieLocal(
            title = "El Orfanato",
            year = 2007,
            image = R.drawable.elorfanato
        )
    )
    val popularWithFriends = listOf(
        MovieLocal(
            title = "Alien: Romulus",
            year = 2024,
            image = R.drawable.alienromulus
        )
    )
}