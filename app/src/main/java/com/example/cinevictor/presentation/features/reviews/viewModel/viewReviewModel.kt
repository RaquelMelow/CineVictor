package com.example.cinevictor.presentation.features.reviews.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinevictor.data.repository.MovieRepository
import com.example.cinevictor.data.repository.ReviewRepository
import com.example.cinevictor.model.Movie
import com.example.cinevictor.presentation.features.reviews.model.ReviewData

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>> get() = _popularMovies

    fun getPopularMovies() {
        _popularMovies.value = movieRepository.getPopularMovie()
    }
}

class ReviewsViewModel(private val reviewRepository: ReviewRepository) : ViewModel() {

    private val _friendsReviews = MutableLiveData<List<ReviewData>>()
    val friendsReviews: LiveData<List<ReviewData>> get() = _friendsReviews

    private val _popularReviews = MutableLiveData<List<ReviewData>>()
    val popularReviews: LiveData<List<ReviewData>> get() = _popularReviews

    fun loadReviews() {
        _friendsReviews.value = reviewRepository.getFriendsReview()
        _popularReviews.value = reviewRepository.getPopularReview()
    }
}