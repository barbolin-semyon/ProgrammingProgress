package com.example.programmingprogress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.programmingprogress.ui.components.BackgroundCard
import com.example.programmingprogress.ui.components.Toolbar
import com.example.programmingprogress.ui.navigation.AppBottomNavigation
import com.example.programmingprogress.ui.navigation.AppNavHost
import com.example.programmingprogress.ui.navigation.Screen
import com.example.programmingprogress.ui.theme.Green
import com.example.programmingprogress.ui.theme.ProgrammingProgressTheme
import com.example.programmingprogress.util.AuthorizationType
import com.example.programmingprogress.viewmodel.AuthViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProgrammingProgressTheme {

                val UIController = rememberSystemUiController()
                UIController.setStatusBarColor(Green)

                val navController = rememberNavController()

                val viewModel: AuthViewModel = viewModel()
                viewModel.checkAuthorization()

                var title by remember { mutableStateOf("progress") }

                Scaffold(
                    topBar = {
                        Toolbar(title, navController, authViewModel = viewModel)
                    },
                    bottomBar = {
                        AppBottomNavigation(
                            navHostController = navController,
                        )
                    }) { padding ->
                    Box(
                        modifier = Modifier
                            .padding(padding)
                            .background(Green)
                    ) {
                        BackgroundCard {
                            AppNavHost(
                                viewModel = viewModel,
                                navController = navController,
                                changeTitle = { title = it }
                            )
                        }
                        ObserverAuthorizationState(viewModel, navHostController = navController)
                    }
                }
            }
        }
    }
}

@Composable
private fun ObserverAuthorizationState(
    viewModel: AuthViewModel,
    navHostController: NavHostController
) {
    val state = viewModel.typeAuthorization.observeAsState()

    val route = when(state.value) {
        AuthorizationType.AUTHORIZATION -> Screen.Main.route
        AuthorizationType.NOT_AUTHORIZATION -> Screen.Authorization.route
        else -> Screen.Splash.route
    }

    navHostController.navigate(route) {
        popUpTo(route) {
            inclusive = true
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}