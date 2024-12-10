package com.example.cinevictor.presentation.features.popular.reviews.viewModel

import androidx.lifecycle.ViewModel
import com.example.cinevictor.data.local.database.ReviewWithMovie
import com.example.cinevictor.data.repository.ReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ReviewsViewModel (
    private val repository: ReviewRepository
): ViewModel() {

    private val _popularReviews = MutableStateFlow<List<ReviewWithMovie>?>(null)
    val popularReviews: StateFlow<List<ReviewWithMovie>?> = _popularReviews

}

