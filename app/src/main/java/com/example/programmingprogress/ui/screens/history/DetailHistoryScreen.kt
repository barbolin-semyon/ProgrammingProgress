package com.example.programmingprogress.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.programmingprogress.R
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.ui.components.BackgroundCard
import com.example.programmingprogress.ui.components.CustomButtonFillSize
import com.example.programmingprogress.ui.components.CustomToolbarWithCalendar
import com.example.programmingprogress.ui.components.TextWithCaption
import com.example.programmingprogress.ui.navigation.HistoryScreen
import com.example.programmingprogress.ui.theme.DarkGray
import com.example.programmingprogress.ui.theme.Green
import com.example.programmingprogress.ui.theme.Red
import com.example.programmingprogress.util.parseToShortString
import com.example.programmingprogress.viewmodel.HistoryViewModel

@Composable
fun DetailHistoryScreen(navHostController: NavHostController, history: History?) {
    var day by remember { mutableStateOf(history) }

    if (day == null) {
        val viewModel = viewModel(HistoryViewModel::class.java)
        val currentDay = viewModel.isLoadTodayHistory.observeAsState()
        LaunchedEffect(key1 = Unit, block = {
            viewModel.getHistoryForToday()
        })

        currentDay.value?.let {
            day = it
        }
    } else {
        BackgroundCard {
            if (day!!.check.not()) {
                NoProgrammingScreen(navHostController, day!!)
            } else {
                Content(day!!)
            }
        }
    }

    CustomToolbarWithCalendar(title = "Детали") {
        navHostController.navigate(HistoryScreen.ListHistoryScreen.route) {
        }
    }


}

@Composable
private fun Content(history: History) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(Modifier.padding(start = 16.dp)) {
            TextWithCaption(
                caption = "Время",
                text = "${history.hours}",
            )

            TextWithCaption(
                caption = "Дата",
                text = history.date.parseToShortString(),
            )
        }


        Card(elevation = 4.dp, shape = RoundedCornerShape(32.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.checkpoint),
                contentDescription = "is program",
                tint = Green,
                modifier = Modifier
                    .size(350.dp)
                    .padding(16.dp)
            )
        }

        DescriptionText(history.description)
    }
}

@Composable
fun NoProgrammingScreen(navHostController: NavHostController, history: History) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "${history.date.parseToShortString()} вы не программировали",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp),
            color = DarkGray,
        )

        Image(
            painter = painterResource(id = R.drawable.panda),
            contentDescription = "panda",
            modifier = Modifier.fillMaxWidth()
        )

        if (history.description.isNotEmpty()) {
            DescriptionText(history.description)
        } else {
            Column {
                CustomButtonFillSize(text = "Исправить", color = Green) {
                    val state = navHostController.currentBackStackEntry?.savedStateHandle
                    state?.set("isSetHours", true)
                    state?.set("history", history)

                    navHostController.navigate(HistoryScreen.InputHistoryScreen.route)
                }

                CustomButtonFillSize(color = Red, text = "Написать причину") {
                    val state = navHostController.currentBackStackEntry?.savedStateHandle
                    state?.set("history", history)

                    navHostController.navigate(HistoryScreen.InputHistoryScreen.route)
                }
            }
        }
    }
}

@Composable
private fun DescriptionText(text: String) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp), elevation = 8.dp) {
        Text(modifier = Modifier.padding(16.dp), text = text, fontSize = 18.sp)
    }
}