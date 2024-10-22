package com.example.cinevictor.data.repository

import com.example.cinevictor.data.local.MoviesReviewStore
import com.example.cinevictor.model.Movie

class MovieRepository (private val movieService: MoviesReviewStore) {
     fun getPopularMovie(): List<Movie> {
         return MoviesReviewStore.popularThisWeek
     }

    fun getFriendsNewMovies(): List<Movie> {
        return MoviesReviewStore.newFromFriends
    }

}