package com.example.programmingprogress.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppBottomNavigation(navHostController: NavHostController) {

    val backStackEntryState = navHostController.currentBackStackEntryAsState()
    val currentDestination = backStackEntryState.value?.destination

    currentDestination?.route?.let { currentRoute ->
        if (NotBottomNavScreens.contains(currentRoute).not()) {
            BottomNavigation {
                BottomNavScreens.forEach { currentScreen ->
                    BottomNavigationItem(
                        selected = currentDestination.hierarchy.any { it.route == currentScreen.route },
                        onClick = {
                            navHostController.navigate(currentRoute) {
                                popUpTo(navHostController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = currentScreen.icon),
                                contentDescription = currentScreen.route
                            )
                        }
                    )
                }
            }
        }
    }
}