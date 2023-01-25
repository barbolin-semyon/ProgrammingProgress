package com.example.programmingprogress.ui.screens.history

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.programmingprogress.ui.components.*
import com.example.programmingprogress.ui.navigation.HistoryScreen
import com.example.programmingprogress.viewmodel.HistoryViewModel
import java.util.*

@Composable
fun ListHistoryView(navHostController: NavHostController) {
    val viewModel: HistoryViewModel = viewModel()
    val history = viewModel.history.observeAsState()
    val currentDate by remember { mutableStateOf(Calendar.getInstance()) }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.enableListenerHistory(currentDate)
    })

    BackgroundCard {
        history.value?.let {
            CalendarView(
                days = history.value!!,
                onClick = {
                    viewModel.disableListener()
                    navHostController.currentBackStackEntry?.savedStateHandle?.set("history", it)
                    navHostController.navigate(HistoryScreen.DetailHistoryScreen.route) {
                        popUpTo(HistoryScreen.ListHistoryScreen.route) {
                            saveState = true
                            inclusive = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                changeMonth = {
                    viewModel.disableListener()
                    currentDate.add(Calendar.MONTH, it)

                    viewModel.enableListenerHistory(tempDate = currentDate.clone() as Calendar)
                }
            )
        }
    }
}
