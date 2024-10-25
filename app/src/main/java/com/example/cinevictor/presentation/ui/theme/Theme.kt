package com.example.cinevictor.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

/*@Composable
fun CineVictorTheme(
   darkTheme: Boolean = isSystemInDarkTheme(),
 Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = colorScheme,
       typography = Typography,
        content = content
    )
}*/

private val TemaNuevo = lightColorScheme(
    primary = LimeGreen,
    onSurface = LimeGreen,
    secondary = CharcoalBlack,
    background = DarkGray
)


@Composable
fun CineTemita(content: @Composable () -> Unit){
    MaterialTheme(
        colorScheme = TemaNuevo,
        typography = Typography,
        content = content,
    )
}