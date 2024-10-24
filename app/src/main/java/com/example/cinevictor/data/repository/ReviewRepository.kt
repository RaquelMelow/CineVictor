package com.example.cinevictor.data.repository

import com.example.cinevictor.R
import com.example.cinevictor.data.local.MoviesReviewStore
import com.example.cinevictor.model.User
import com.example.cinevictor.presentation.features.popular.reviews.model.ReviewData

class ReviewRepository {
    fun getFriendsReview(): List<ReviewData> {
        return listOf(
            ReviewData(
                id = "1",
                user = User(id = "u0", name = "Amanda", R.drawable.perfilamanda),
                movie = MoviesReviewStore.popularWithFriends[0],
                rating = 4,
                comment = "Flojita",
                date = "2024-09-29"
            )
        )
    }

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
            )
        )
    }
}
