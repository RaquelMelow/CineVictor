package com.example.cinevictor.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItems(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)