package com.example.programmingprogress.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.programmingprogress.R
import com.example.programmingprogress.ui.navigation.BottomNavScreens
import com.example.programmingprogress.ui.navigation.HistoryScreen
import com.example.programmingprogress.ui.navigation.Screen
import com.example.programmingprogress.ui.theme.Green
import com.example.programmingprogress.ui.theme.White

@Composable
fun Toolbar(title: String, navHostController: NavHostController) {
    val state = navHostController.currentBackStackEntryAsState()
    val route = state.value?.destination?.route

    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Green,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h4,
            )
        },
        navigationIcon = {
            if (route in BottomNavScreens.map { it.route }) {
                IconButton(onClick = { navHostController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back",
                        tint = White,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(start = 8.dp)
                    )
                }
            }
        },
        actions = {
            if (route == HistoryScreen.DetailHistoryScreen.route) {
                IconButton(onClick = {
                    navHostController.navigate(HistoryScreen.ListHistoryScreen.route)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = "открыть календарь",
                        tint = White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    )
}