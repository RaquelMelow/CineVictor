package com.example.cinevictor.presentation.ui.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun convertStringToDate(dateString: String): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDate.parse(dateString, formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun getYearFromDate(date: LocalDate): String {
    return date.year.toString()
}