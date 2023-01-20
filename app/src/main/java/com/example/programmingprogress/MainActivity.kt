package com.example.programmingprogress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.programmingprogress.ui.navigation.AppBottomNavigation
import com.example.programmingprogress.ui.navigation.AppNavHost
import com.example.programmingprogress.ui.navigation.Screen
import com.example.programmingprogress.ui.theme.DarkMagenta
import com.example.programmingprogress.ui.theme.ProgrammingProgressTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProgrammingProgressTheme {

                val UIController = rememberSystemUiController()
                UIController.setStatusBarColor(DarkMagenta)

                val navController = rememberNavController()
                val startDestination = Screen.Main.route

                Scaffold(bottomBar = {
                    AppBottomNavigation(
                        navHostController = navController,
                    )
                }) {
                    Box(modifier = Modifier.padding(it).background(DarkMagenta)) {
                        AppNavHost(
                            navController = navController,
                            startDestination = startDestination
                        )
                    }
                }
            }
        }
    }
}

