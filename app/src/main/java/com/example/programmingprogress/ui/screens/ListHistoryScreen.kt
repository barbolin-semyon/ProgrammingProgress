package com.example.programmingprogress.ui.screens

import android.util.Log
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
import com.example.programmingprogress.ui.components.BackgroundCard
import com.example.programmingprogress.ui.components.CardForHistory
import com.example.programmingprogress.ui.components.ClickType
import com.example.programmingprogress.ui.components.CustomToolbarForHistory
import com.example.programmingprogress.ui.navigation.HistoryScreen
import com.example.programmingprogress.viewmodel.HistoryViewModel

@Composable
fun ListHistoryView(navHostController: NavHostController) {
    val viewModel: HistoryViewModel = viewModel()
    val history = viewModel.history.observeAsState()
    LaunchedEffect(key1 = history, block = {
        viewModel.enableListenerHistory()
    })

    var animated by remember { mutableStateOf(true) }
    val rotation = remember { Animatable(initialValue = 180f) }
    LaunchedEffect(key1 = animated, block = {
        rotation.animateTo(
            targetValue = if (animated) 0f else 180f,
            animationSpec = tween(durationMillis = 1000)
        )
    })

    CustomToolbarForHistory(title = "Детали", navHostController = navHostController)
    BackgroundCard(
        topPadding = 90.dp,
        angleRound = 90.dp,
        onClick = { isTapRightCard ->
            animated = animated.not()

            val type = if (isTapRightCard.not().xor(animated)
            ) ClickType.LEFT_CLICK else ClickType.RIGHT_CLICK

            clickBackground(type, viewModel)
        },
        rotation = rotation.value,
    ) {
        history.value?.let {
            Content(history.value!!, rotation.value, navHostController)
        }
    }
}

private fun clickBackground(typeClick: ClickType, viewModel: HistoryViewModel) {
    if (typeClick == ClickType.LEFT_CLICK) {
        viewModel.decreaseCurrentDate()
    } else {
        viewModel.increaseCurrentDate()
    }
}

@Composable
private fun Content(
    histories: List<History?>,
    rotation: Float,
    navHostController: NavHostController
) {
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        items(histories) {
            CardForHistory(history = it!!, rotation = rotation) {
                navHostController.currentBackStackEntry?.savedStateHandle?.set("history", it)
                navHostController.navigate(HistoryScreen.DetailHistoryScreen.route) {
                    popUpTo(navHostController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }

    }
}