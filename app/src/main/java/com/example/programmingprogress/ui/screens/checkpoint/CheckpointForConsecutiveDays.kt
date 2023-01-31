package com.example.programmingprogress.ui.screens.checkpoint

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.programmingprogress.ui.theme.Green
import com.example.programmingprogress.ui.theme.Orange
import com.example.programmingprogress.viewmodel.HistoryViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CheckpointForConsecutiveDays(oldCount: Int) {
    val viewModel = viewModel(HistoryViewModel::class.java)
    val newCount = viewModel.countConsecutiveDays.observeAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getCountConsecutiveDays()
    })

    if (newCount.value != 0) {
        LazyVerticalGrid(modifier = Modifier.fillMaxSize(), cells = GridCells.Adaptive(90.dp), content = {
            items(oldCount) {
                ItemDay(color = Orange)
            }

            items(newCount.value!!.minus(oldCount)) {
                ItemDay(color = Green)
            }
        })
    }
}

@Composable
private fun ItemDay(color: Color) {
    Card(
        modifier = Modifier
            .size(90.dp)
            .padding(8.dp),
        backgroundColor = color
    ) {}
}