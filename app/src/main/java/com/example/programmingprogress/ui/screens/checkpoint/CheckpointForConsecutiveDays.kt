package com.example.programmingprogress.ui.screens.checkpoint

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.programmingprogress.ui.theme.DarkGray
import com.example.programmingprogress.ui.theme.Green
import com.example.programmingprogress.ui.theme.Orange
import com.example.programmingprogress.ui.theme.White
import com.example.programmingprogress.viewmodel.HistoryViewModel
import com.example.programmingprogress.viewmodel.UserViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CheckpointForConsecutiveDays(oldCount: Int) {
    val historyViewModel = viewModel(HistoryViewModel::class.java)
    val userViewModel = viewModel(UserViewModel::class.java)
    val newCount = historyViewModel.countConsecutiveDays.observeAsState()

    LaunchedEffect(key1 = Unit, block = {
        historyViewModel.getCountConsecutiveDays()
    })

    if (newCount.value != 0) {
        Box(contentAlignment = Alignment.BottomCenter)   {
            val razn = newCount.value!!.minus(oldCount)
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize().padding(bottom = 32.dp),
                cells = GridCells.Adaptive(90.dp),
                content = {
                    items(oldCount) {
                        ItemDay(color = Orange)
                    }

                    items(razn) {
                        ItemDay(color = Green)
                    }
                })

            if (razn > 0) {
                Button(
                    onClick = { userViewModel.updateCountConsecutiveDays(newCount.value!!) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(Green)
                ) {
                    Text("Получить награду за побитый рекорд", color = White)
                }
                val state = userViewModel.isRequestSuccess.observeAsState()
                if (state.value == true) {
                    Text("Очки начислены", color = DarkGray)
                }
            }
        }
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