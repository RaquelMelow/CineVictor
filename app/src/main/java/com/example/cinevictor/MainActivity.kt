package com.example.cinevictor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.cinevictor.data.network.MovieService
import com.example.cinevictor.data.network.RetrofitClient
import com.example.cinevictor.presentation.features.popular.CineVictorNavigationDrawer
import com.example.cinevictor.presentation.ui.theme.CineVictorTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineVictorTheme {
                CineVictorNavigationDrawer()
            }
        }
    }
}

