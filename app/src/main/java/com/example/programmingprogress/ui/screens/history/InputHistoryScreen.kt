package com.example.programmingprogress.ui.screens.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.ui.components.Counter
import com.example.programmingprogress.ui.components.TextWithCaption
import com.example.programmingprogress.util.parseToShortString
import com.example.programmingprogress.viewmodel.HistoryViewModel
import com.example.programmingprogress.viewmodel.UserViewModel

@Composable
fun InputHistoryScreen(
    navHostController: NavHostController,
    history: History,
    isSetHours: Boolean
) {
    val historyViewModel: HistoryViewModel = viewModel()
    val userViewModel: UserViewModel = viewModel()

    var counterHours by remember { mutableStateOf(0.0) }

    val isNavigateBack = historyViewModel.isNavigateBack.observeAsState()
    if (isNavigateBack.value == true) {
        userViewModel.getInformationUser()
    }

    val userInfo = userViewModel.userInfo.observeAsState()
    if (userInfo.value?.id != "") {
        userViewModel.updateUserValueOfDay(counterHours)
    }

    val state = userViewModel.isRequestSuccess.observeAsState()
    if (state.value == true) {
        navHostController.popBackStack()
    }

    Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp)) {
        TextWithCaption(
            caption = "Дата",
            text = history.date.parseToShortString()
        )

        if (isSetHours) {
            Counter(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .height(100.dp),
                value = counterHours,
                step = 0.5,
                valueChange = { counterHours = it }
            )
        }

        var tempDescription by remember { mutableStateOf("") }
        TextField(
            value = tempDescription,
            onValueChange = { tempDescription = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(onClick = {
            history.apply {
                if (isSetHours) {
                    check = true
                    hours = counterHours
                }
                description = tempDescription
            }
            historyViewModel.updateHistory(history)
        }) {
            Text(text = "Сохранить")
        }
    }
}