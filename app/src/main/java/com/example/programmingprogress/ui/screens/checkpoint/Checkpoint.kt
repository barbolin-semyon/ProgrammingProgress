package com.example.programmingprogress.ui.screens.checkpoint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.programmingprogress.ui.components.Item
import com.example.programmingprogress.ui.components.Spinner
import com.example.programmingprogress.ui.theme.*
import com.example.programmingprogress.viewmodel.UserViewModel

@Composable
fun Checkpoint(navHostController: NavHostController) {
    val viewModel = viewModel(UserViewModel::class.java)

    val user = viewModel.userInfo.observeAsState()
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getInformationUser()
    })

    var currentLabel by remember { mutableStateOf("Категория не известна") }

    user.value?.let { user ->
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
                    CheckpointViewForHoursOrDay(user.countOfDaysSuccess)
                }
                CheckpointCategory.HOURS.text -> {
                    CheckpointViewForHoursOrDay(user.hours)
                }
                CheckpointCategory.CONSECUTIVE_DAYS.text -> {
                    CheckpointForConsecutiveDays(user.countOfConsecutiveDaysSuccess)
                }
            }
        }
    }
}