package com.example.cinevictor.presentation.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemUiConfig(
    statusBarColor: Color,
    useDarkIcons: Boolean = false
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = statusBarColor,
        darkIcons = useDarkIcons
    )
}
