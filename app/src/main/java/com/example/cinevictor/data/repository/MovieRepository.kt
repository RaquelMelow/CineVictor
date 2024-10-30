package com.example.cinevictor.data.repository

import com.example.cinevictor.data.local.MoviesReviewStore
import com.example.cinevictor.model.MovieLocal

class MovieRepository (private val movieService: MoviesReviewStore) {
     fun getPopularMovie(): List<MovieLocal> {
         return MoviesReviewStore.popularThisWeek
     }

    fun getFriendsNewMovies(): List<MovieLocal> {
        return MoviesReviewStore.newFromFriends
    }

}