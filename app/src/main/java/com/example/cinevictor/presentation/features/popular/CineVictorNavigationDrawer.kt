package com.example.cinevictor.presentation.features.popular

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinevictor.domain.model.NavigationItems
import com.example.cinevictor.presentation.features.popular.films.view.FilmsScreen
import com.example.cinevictor.presentation.features.popular.journal.view.JournalRoute
import com.example.cinevictor.presentation.features.popular.lists.view.ListsScreen
import com.example.cinevictor.presentation.features.reviews.view.ReviewsScreen
import com.example.cinevictor.presentation.ui.util.SystemUiConfig
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CineVictorNavigationDrawer(
    navigateToDetail: (id: Int) -> Unit,
    statusBarColor: Color = Color(0xFF727272),
    useDarkIcons: Boolean = false
) {
    val items = listOf(
        NavigationItems("Popular", Icons.Filled.Menu, Icons.Outlined.Menu),
        NavigationItems("Search", Icons.Filled.Search, Icons.Outlined.Search),
        NavigationItems("Profile", Icons.Filled.Person, Icons.Outlined.Person),
        NavigationItems("Settings", Icons.Filled.Settings, Icons.Outlined.Settings),
        NavigationItems("List", Icons.AutoMirrored.Filled.List, Icons.AutoMirrored.Outlined.List),
        NavigationItems("Reviews", Icons.Filled.Star, Icons.Outlined.Star)
    )

    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val tabs = listOf("FILMS", "REVIEWS", "LISTS", "JOURNAL")

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color(0xFF727272),
                drawerShape = RectangleShape,
                drawerContentColor = Color.White,
                modifier = Modifier.fillMaxSize()
            ) {
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title, color = Color.White) },
                        icon = {
                            Icon(
                                imageVector = item.selectedIcon,
                                contentDescription = null,
                                tint = Color.White
                            )
                        },
                        selected = index == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = Color.Transparent,
                            selectedContainerColor = Color(0xFFA2A2A2)
                        ),
                        shape = RectangleShape
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        SystemUiConfig(statusBarColor = statusBarColor, useDarkIcons = useDarkIcons)

        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .consumeWindowInsets(WindowInsets.statusBars),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF727272)
                    ),
                    title = {
                        Text(
                            text = "Popular",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }
                    }
                )
            },
        ) { innerPadding ->
            var selectedTab by remember { mutableIntStateOf(0) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = Color(0xFF727272),
                    indicator = { tabPositions ->
                        SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            color = Color(0xFF00FF00)
                        )
                    }
                ) {
                    tabs.forEachIndexed { index, tabName ->
                        Tab(
                            modifier = Modifier.width(100.dp),
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = {
                                Text(
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    text = tabName,
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        )
                    }
                }

                when (selectedTab) {
                    0 -> FilmsScreen(navigateToDetail = navigateToDetail)
                    1 -> ReviewsScreen()
                    2 -> ListsScreen()
                    3 -> JournalRoute()
                }
            }
        }
    }
}