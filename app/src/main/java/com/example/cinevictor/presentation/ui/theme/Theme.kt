package com.example.cinevictor.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val TemaNuevo = lightColorScheme(
    primary = LimeGreen,
    onPrimary = DarkGray,
    surfaceContainerHigh = DarkGray,
    onSurface = LimeGreen,
    secondary = CharcoalBlack,
    background = DarkGray,
)

@Composable
fun CineTemita(content: @Composable () -> Unit){
    MaterialTheme(
        colorScheme = TemaNuevo,
        typography = Typography,
        content = content,
    )
}