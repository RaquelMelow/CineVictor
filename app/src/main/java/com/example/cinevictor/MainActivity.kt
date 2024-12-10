package com.example.cinevictor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.cinevictor.presentation.navigation.AppNavHost
import com.example.cinevictor.presentation.ui.theme.CineTemita

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineTemita {
                Scaffold(Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

