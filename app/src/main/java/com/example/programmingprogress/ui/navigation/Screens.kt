package com.example.programmingprogress.ui.navigation

import com.example.programmingprogress.R

sealed class Screen(val route: String) {
    object Authorization : Screen("authorization")
    object Main: Screen("main")
}

sealed class AuthorizationScreen(val route: String) {
    object SignInScreen : AuthorizationScreen("signIn")
    object RegistrationScreen : AuthorizationScreen("registration")
}

sealed class MainScreen(val route: String, val icon: Int) {
    object Checkpoints : MainScreen("checkpoint", R.drawable.checkpoint)
    object Rating : MainScreen("rating", R.drawable.rating)
    object History : MainScreen("history", R.drawable.profile)
}

sealed class HistoryScreen(val route: String) {
    object ListHistoryScreen: HistoryScreen("listHistory")
    object DetailHistoryScreen: HistoryScreen("detailHistory")
}

val BottomNavScreens =
    listOf(MainScreen.Checkpoints, MainScreen.History, MainScreen.Rating)

val NotBottomNavScreens = listOf(
    AuthorizationScreen.SignInScreen.route,
    AuthorizationScreen.RegistrationScreen.route
)
