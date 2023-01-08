package com.example.programmingprogress.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.programmingprogress.ui.components.BackgroundCard
import com.example.programmingprogress.ui.components.CardForHistory
import com.example.programmingprogress.ui.components.CustomToolbarForHistory
import com.example.programmingprogress.viewmodel.HistoryViewModel
import java.util.*

@Composable
fun ListHistoryView(navHostController: NavHostController) {
    val viewModel: HistoryViewModel = viewModel()
    val history = viewModel.history.observeAsState()
    LaunchedEffect(key1 = history, block = {
        viewModel.enableListenerHistory(Calendar.getInstance())
    })

    var animated by remember { mutableStateOf(false) }
    val rotation = remember { Animatable(initialValue = 360f) }
    LaunchedEffect(key1 = animated, block = {
        rotation.animateTo(
            targetValue = if (animated) 0f else 180f,
            animationSpec = tween(durationMillis = 400)
        )
    })

    CustomToolbarForHistory(title = "Детали", navHostController = navHostController)
    BackgroundCard(topPadding = 90.dp, angleRound = 90.dp, rotation = rotation.value) {
        history.value?.let {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                items(history.value!!) { history ->

                    CardForHistory(history!!, rotation.value, onClick = {

                    })
                }
            }
        }
    }
}