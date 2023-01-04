package com.example.programmingprogress.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

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

        }

        composable(route = HistoryScreen.ListHistoryScreen.route) {

        }
    }
}