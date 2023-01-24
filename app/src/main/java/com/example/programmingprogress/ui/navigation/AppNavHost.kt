package com.example.programmingprogress.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.ui.screens.DetailHistoryScreen
import com.example.programmingprogress.ui.screens.ListHistoryView
import com.example.programmingprogress.ui.screens.history.InputHistoryScreen

@Composable
fun AppNavHost(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        main(navController = navController)
        authorization(navController = navController)
    }
}

private fun NavGraphBuilder.authorization(navController: NavHostController) {
    navigation(
        startDestination = AuthorizationScreen.SignInScreen.route,
        route = Screen.Authorization.route
    ) {
        composable(route = AuthorizationScreen.SignInScreen.route) {

        }

        composable(route = AuthorizationScreen.RegistrationScreen.route) {

        }
    }
}

private fun NavGraphBuilder.main(navController: NavHostController) {
    navigation(startDestination = MainScreen.History.route, route = Screen.Main.route) {
        history(navController = navController)

        composable(route = MainScreen.Rating.route) {

        }

        composable(route = MainScreen.Checkpoints.route) {

        }
    }
}

private fun NavGraphBuilder.history(navController: NavHostController) {
    navigation(
        startDestination = HistoryScreen.DetailHistoryScreen.route,
        route = MainScreen.History.route
    ) {
        composable(route = HistoryScreen.DetailHistoryScreen.route) {
            var history =
                navController.previousBackStackEntry?.savedStateHandle?.get<History>("history")

            DetailHistoryScreen(navController, history)
        }

        composable(route = HistoryScreen.ListHistoryScreen.route) {
            ListHistoryView(navController)
        }

        composable(route = HistoryScreen.InputHistoryScreen.route) {
            val savedState = navController.previousBackStackEntry?.savedStateHandle
            val isSetHours = savedState?.get<Boolean>("isSetHours") ?: false
            val history = savedState?.get<History>("history")

            history?.let {
                InputHistoryScreen(
                    navHostController = navController,
                    history = it,
                    isSetHours = isSetHours
                )
            }
        }
    }
}
