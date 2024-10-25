package com.example.cinevictor.data.local

import com.example.cinevictor.R
import com.example.cinevictor.model.Movie

object MoviesReviewStore {
    val popularThisWeek = listOf(
        Movie(
            title = "Challenger ",
            year = 2024,
            image = R.drawable.challenger
        ),
        Movie(
            title = "Dune: Part II ",
            year = 2023,
            image = R.drawable.dune2
        ),
        Movie(
            title = "El Hoyo 2 ",
            year = 2024,
            image = R.drawable.elhoyo2
        ),
        Movie(
            title = "Sherk ",
            year = 2001,
            image = R.drawable.sherk
        ),
        Movie(
            title = "Midsommar ",
            year = 2019,
            image = R.drawable.midsommar
        )
    )
    val newFromFriends = listOf(
        Movie(
            title = "Smile 2 ",
            year = 2024,
            image = R.drawable.smile2
        ),
        Movie(
            title = "The Ring ",
            year = 1999,
            image = R.drawable.thering
        ),
        Movie(
            title = "As Bestas ",
            year = 2022,
            image = R.drawable.asbestas
        ),
        Movie(
            title = "El Orfanato ",
            year = 2007,
            image = R.drawable.elorfanato
        )
    )
    val popularWithFriends = listOf(
        Movie(
            title = "Alien: Romulus ",
            year = 2024,
            image = R.drawable.alienromulus
        )
    )
}