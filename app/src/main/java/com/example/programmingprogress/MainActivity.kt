package com.example.programmingprogress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.programmingprogress.ui.navigation.AppBottomNavigation
import com.example.programmingprogress.ui.navigation.AppNavHost
import com.example.programmingprogress.ui.navigation.Screen
import com.example.programmingprogress.ui.theme.Orange
import com.example.programmingprogress.ui.theme.ProgrammingProgressTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProgrammingProgressTheme {

                val UIController = rememberSystemUiController()
                UIController.setStatusBarColor(Orange)

                val navController = rememberNavController()
                val startDestination = Screen.Main.route

                Scaffold(bottomBar = {
                    AppBottomNavigation(
                        navHostController = navController,
                    )
                }) {
                    Box(modifier = Modifier.padding(it)) {
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

