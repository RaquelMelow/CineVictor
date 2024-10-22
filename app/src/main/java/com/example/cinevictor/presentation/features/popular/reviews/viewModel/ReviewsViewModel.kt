package com.example.cinevictor.presentation.features.popular.reviews.viewModel

import androidx.lifecycle.ViewModel
import com.example.cinevictor.data.repository.ReviewRepository
import com.example.cinevictor.presentation.features.popular.reviews.model.ReviewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ReviewsViewModel : ViewModel() {

    private val reviewRepository: ReviewRepository = ReviewRepository()

    private val _friendsReviews = MutableStateFlow<List<ReviewData>>(emptyList())
    val friendsReviews: StateFlow<List<ReviewData>> get() = _friendsReviews

    private val _popularReviews = MutableStateFlow<List<ReviewData>>(emptyList())
    val popularReviews: StateFlow<List<ReviewData>> get() = _popularReviews

    init {
        loadReviews()
    }

    fun loadReviews() {
        _friendsReviews.value = reviewRepository.getFriendsReview()
        _popularReviews.value = reviewRepository.getPopularReview()
    }
}