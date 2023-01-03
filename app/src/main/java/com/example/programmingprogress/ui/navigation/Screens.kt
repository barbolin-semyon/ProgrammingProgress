package com.example.programmingprogress.ui.navigation

import com.example.programmingprogress.R

sealed class Screen(route: String) {
    object Authorization : Screen("authorization")
    object Main: Screen("main")
}

sealed class AuthorizationScreen(route: String) {
    object SignInScreen : AuthorizationScreen("signIn")
    object RegistrationScreen : AuthorizationScreen("registration")
}

sealed class MainScreen(route: String, icon: Int) {
    object Checkpoints : MainScreen("checkpoint", R.drawable.checkpoint)
    object Diagram : MainScreen("diagram", R.drawable.diagram)
    object History : MainScreen("history", R.drawable.profile)
}

sealed class HistoryScreen(route: String) {
    object ListHistoryScreen: HistoryScreen("listHistory")
    object DetailHistoryScreen: HistoryScreen("detailHistory")
}