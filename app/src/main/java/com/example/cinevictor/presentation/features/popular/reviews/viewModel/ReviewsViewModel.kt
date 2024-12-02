package com.example.cinevictor.presentation.features.popular.reviews.viewModel

import androidx.lifecycle.ViewModel
import com.example.cinevictor.data.repository.ReviewRepositoryLocal
import com.example.cinevictor.presentation.features.popular.reviews.model.ReviewDatalLocal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ReviewsViewModel : ViewModel() {

    private val reviewRepository: ReviewRepositoryLocal = ReviewRepositoryLocal()

    private val _popularReviews = MutableStateFlow<List<ReviewDatalLocal>>(emptyList())
    val popularReviews: StateFlow<List<ReviewDatalLocal>> get() = _popularReviews

    init {
        loadReviews()
    }

    fun loadReviews() {
        _popularReviews.value = reviewRepository.getPopularReview()
    }
}