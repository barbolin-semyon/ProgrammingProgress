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

    var currentCount by remember { mutableStateOf(0) }

    user.value?.let { user ->
        Column {
            Spinner(
                items = listOf(
                    CheckpointCategory.HOURS,
                    CheckpointCategory.CONSECUTIVE_DAYS,
                    CheckpointCategory.ALL_DAY,
                ),
                hint = "Выберите тип",
                padding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                onClick = { catecory ->
                    currentCount = when (catecory) {
                        CheckpointCategory.ALL_DAY -> user.countOfDaysSuccess
                        CheckpointCategory.HOURS -> user.hours
                        CheckpointCategory.CONSECUTIVE_DAYS -> user.countOfConsecutiveDaysSuccess
                    }
                }
            )

            CheckpointViewForHoursOrDay(currentCount)
        }
    }
}