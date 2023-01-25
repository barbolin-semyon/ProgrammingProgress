package com.example.programmingprogress.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
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
    val destination = state.value?.destination

    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Green,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h4,
            )
        },
        actions = {
            if (destination?.route == HistoryScreen.DetailHistoryScreen.route) {
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