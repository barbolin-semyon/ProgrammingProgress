package com.example.programmingprogress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.programmingprogress.ui.components.Toolbar
import com.example.programmingprogress.ui.navigation.AppBottomNavigation
import com.example.programmingprogress.ui.navigation.AppNavHost
import com.example.programmingprogress.ui.navigation.Screen
import com.example.programmingprogress.ui.theme.Green
import com.example.programmingprogress.ui.theme.ProgrammingProgressTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProgrammingProgressTheme {

                val UIController = rememberSystemUiController()
                UIController.setStatusBarColor(Green)

                val navController = rememberNavController()
                val startDestination = Screen.Main.route
                var title by remember { mutableStateOf("progress")}

                Scaffold(
                    topBar = {
                        Toolbar(title, navController)
                    },
                    bottomBar = {
                        AppBottomNavigation(
                            navHostController = navController,
                        )
                    }) {
                    Box(modifier = Modifier
                        .padding(it)
                        .background(Green)) {
                        AppNavHost(
                            navController = navController,
                            startDestination = startDestination,
                            changeTitle = {title = it}
                        )
                    }
                }
            }
        }
    }
}

