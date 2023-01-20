package com.example.programmingprogress.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.programmingprogress.ui.theme.LightRed
import com.example.programmingprogress.ui.theme.White

@Composable
fun AppBottomNavigation(navHostController: NavHostController) {

    val backStackEntryState = navHostController.currentBackStackEntryAsState()
    val currentDestination = backStackEntryState.value?.destination

    currentDestination?.route?.let { currentRoute ->
        if (NotBottomNavScreens.contains(currentRoute).not()) {
            BottomNavigation {
                BottomNavScreens.forEach { currentScreen ->

                    val select = currentDestination.hierarchy.any {
                        val route = it.route
                        route == currentScreen.route
                    }

                    BottomNavigationItem(
                        selected = select,
                        selectedContentColor = White,
                        unselectedContentColor = LightRed,
                        onClick = {
                            navHostController.navigate(currentRoute) {
                                popUpTo(currentRoute) {
                                    saveState = true
                                    inclusive = true
                                }
                                launchSingleTop = true
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