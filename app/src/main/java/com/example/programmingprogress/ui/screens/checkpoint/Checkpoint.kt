package com.example.programmingprogress.ui.screens.checkpoint

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.programmingprogress.ui.components.Spinner
import com.example.programmingprogress.viewmodel.UserViewModel

@Composable
fun Checkpoint() {
    val viewModel = viewModel(UserViewModel::class.java)

    val user = viewModel.userInfo.observeAsState()
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getInformationUser()
    })

    var currentLabel by remember { mutableStateOf("Категория не известна") }

    user.value?.let { userNoNull ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Spinner(
                items = listOf(
                    CheckpointCategory.HOURS,
                    CheckpointCategory.CONSECUTIVE_DAYS,
                    CheckpointCategory.ALL_DAY,
                ),
                hint = "Выберите тип",
                onClick = { catecory ->
                    currentLabel = catecory.text
                }
            )

            Text(text = currentLabel)

            when (currentLabel) {
                CheckpointCategory.ALL_DAY.text -> {
                    CheckpointViewForHoursOrDay(userNoNull.countOfDaysSuccess)
                }
                CheckpointCategory.HOURS.text -> {
                    CheckpointViewForHoursOrDay(userNoNull.hours)
                }
                CheckpointCategory.CONSECUTIVE_DAYS.text -> {
                    CheckpointForConsecutiveDays(userNoNull.countOfConsecutiveDaysSuccess)
                }
            }
        }
    }
}