package com.example.programmingprogress.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.ui.screens.authorization.RegisterView
import com.example.programmingprogress.ui.screens.authorization.SignInView
import com.example.programmingprogress.ui.screens.history.DetailHistoryScreen
import com.example.programmingprogress.ui.screens.history.ListHistoryView
import com.example.programmingprogress.ui.screens.checkpoint.Checkpoint
import com.example.programmingprogress.ui.screens.history.InputHistoryScreen
import com.example.programmingprogress.ui.screens.rating.RatingView
import com.example.programmingprogress.ui.screens.splash.SplashView
import com.example.programmingprogress.viewmodel.AuthViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    changeTitle: (title: String) -> Unit,
    viewModel: AuthViewModel
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        main(navController = navController, changeTitle = changeTitle)
        authorization(navController = navController, changeTitle = changeTitle, viewModel = viewModel)
        composable(route = Screen.Splash.route) {
            SplashView()
        }
    }
}

private fun NavGraphBuilder.authorization(
    navController: NavHostController,
    changeTitle: (title: String) -> Unit,
    viewModel: AuthViewModel
) {
    navigation(
        startDestination = AuthorizationScreen.SignInScreen.route,
        route = Screen.Authorization.route
    ) {
        composable(route = AuthorizationScreen.SignInScreen.route) {
            changeTitle("Авторизация")
            SignInView(navHostController = navController, authViewModel = viewModel)
        }

        composable(route = AuthorizationScreen.RegistrationScreen.route) {
            changeTitle("Регистрация")
            RegisterView(authViewModel = viewModel)
        }
    }
}

private fun NavGraphBuilder.main(
    navController: NavHostController,
    changeTitle: (title: String) -> Unit
) {
    navigation(startDestination = MainScreen.History.route, route = Screen.Main.route) {
        history(navController = navController, changeTitle = changeTitle)

        composable(route = MainScreen.Rating.route) {
            changeTitle("Рейтинг")
            RatingView()
        }

        composable(route = MainScreen.Checkpoints.route) {
            changeTitle("Достижения")
            Checkpoint()
        }
    }
}

private fun NavGraphBuilder.history(
    navController: NavHostController,
    changeTitle: (title: String) -> Unit
) {
    navigation(
        startDestination = HistoryScreen.DetailHistoryScreen.route,
        route = MainScreen.History.route
    ) {
        composable(route = HistoryScreen.DetailHistoryScreen.route) {
            changeTitle("Детали")

            var history =
                navController.previousBackStackEntry?.savedStateHandle?.get<History>("history")

            DetailHistoryScreen(navController, history)
        }

        composable(route = HistoryScreen.ListHistoryScreen.route) {
            changeTitle("Календарь")
            ListHistoryView(navController)
        }

        composable(route = HistoryScreen.InputHistoryScreen.route) {
            changeTitle("Ввод информации")

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
