package com.example.cinevictor.presentation.ui.util

import com.example.cinevictor.data.model.movie.Cast

fun getDirectorFromCast(cast: List<Cast>): Cast? {
    return cast.find { it.job.equals("Director", ignoreCase = true) }
}
