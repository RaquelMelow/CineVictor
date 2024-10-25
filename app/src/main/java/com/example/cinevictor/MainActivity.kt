package com.example.cinevictor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cinevictor.presentation.features.popular.CineVictorNavigationDrawer
import com.example.cinevictor.presentation.ui.theme.CineTemita
//import com.example.cinevictor.presentation.ui.theme.CineVictorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineTemita {
                CineVictorNavigationDrawer()
            }
        }
    }
}

