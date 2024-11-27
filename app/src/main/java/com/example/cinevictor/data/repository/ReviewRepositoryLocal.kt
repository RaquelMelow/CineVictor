package com.example.cinevictor.data.repository

import com.example.cinevictor.R
import com.example.cinevictor.data.local.MoviesReviewStore
import com.example.cinevictor.domain.model.User
import com.example.cinevictor.presentation.features.popular.reviews.model.ReviewData

class ReviewRepositoryLocal {
    fun getPopularReview(): List<ReviewData> {
        return listOf(
            ReviewData(
                id = "1",
                user = User(id = "u1", name = "Juan", R.drawable.perfiljuan),
                movie = MoviesReviewStore.popularThisWeek[0],
                rating = 8,
                comment = "Excelente película",
                date = "2024-09-29"
            ),
            ReviewData(
                id = "2",
                user = User(id = "u2", name = "María", R.drawable.perfilmaria),
                movie = MoviesReviewStore.popularThisWeek[1],
                rating = 7,
                comment = "Muy entretenida",
                date = "2024-09-28"
            ),
            ReviewData(
                id = "1",
                user = User(id = "u0", name = "Amanda", R.drawable.perfilamanda),
                movie = MoviesReviewStore.popularThisWeek[3],
                rating = 4,
                comment = "Flojita",
                date = "2024-09-29"
            ),
        )
    }
}
