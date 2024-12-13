package com.example.cinevictor.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.cinevictor.presentation.features.login.view.LoginScreen
import com.example.cinevictor.presentation.features.popular.CineVictorNavigationDrawer
import com.example.cinevictor.presentation.features.popular.details.view.MovieDetailScreen
import com.example.cinevictor.presentation.features.register.view.RegisterFormScreen
import com.example.cinevictor.presentation.navigation.destinations.AppRoute



@SuppressLint("NewApi")
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppRoute.Popular
    ) {
        composable<AppRoute.Login> {
            LoginScreen(
                navigationToHome = {
                    navController.navigate(AppRoute.Popular)
                },
                navigationToRegister = {
                    navController.navigate(AppRoute.Register)
                }
            )
        }

        composable<AppRoute.Register> {
            RegisterFormScreen(
                onRegisterSuccess = {
                    navController.navigate(AppRoute.Login)
                },
            )
        }

        composable<AppRoute.Popular> {
            CineVictorNavigationDrawer(
                navigateToDetail = { movieId ->
                    navController.navigate(AppRoute.Detail(movieId))
                },
            )
        }

        composable<AppRoute.Detail> { backStackEntry ->
            val route = backStackEntry.toRoute<AppRoute.Detail>()
            MovieDetailScreen(movieId = route.id,  navController = navController)
        }
    }
}


