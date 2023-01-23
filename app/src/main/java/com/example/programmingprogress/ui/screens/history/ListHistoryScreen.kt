package com.example.programmingprogress.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.ui.components.*
import com.example.programmingprogress.ui.navigation.HistoryScreen
import com.example.programmingprogress.viewmodel.HistoryViewModel
import java.util.*

@Composable
fun ListHistoryView(navHostController: NavHostController) {
    val viewModel: HistoryViewModel = viewModel()
    val history = viewModel.history.observeAsState()
    var currentDate by remember { mutableStateOf(Calendar.getInstance()) }


    CustomToolbar(title = "Детали")

    BackgroundCard(
        topPadding = 90.dp,
        angleRound = 90.dp,
    ) {
        history.value?.let {
            CalendarView(
                days = history.value!!,
                onClick = {
                    viewModel.disableListener()
                    navHostController.currentBackStackEntry?.savedStateHandle?.set("history", it)
                    navHostController.navigate(HistoryScreen.DetailHistoryScreen.route)
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
