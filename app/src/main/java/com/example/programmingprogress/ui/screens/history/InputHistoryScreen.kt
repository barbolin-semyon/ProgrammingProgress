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
import com.example.programmingprogress.ui.components.BackgroundCard
import com.example.programmingprogress.ui.components.Counter
import com.example.programmingprogress.ui.components.CustomToolbar
import com.example.programmingprogress.ui.components.TextWithCaption
import com.example.programmingprogress.util.parseToShortString
import com.example.programmingprogress.viewmodel.HistoryViewModel

@Composable
fun InputHistoryScreen(
    navHostController: NavHostController,
    history: History,
    isSetHours: Boolean
) {
    val viewModel: HistoryViewModel = viewModel()

    val isNavigateBack = viewModel.isNavigateBack.observeAsState()
    if (isNavigateBack.value == true) {
        navHostController.popBackStack()
    }

    CustomToolbar("Ввод информации", navHostController)
    BackgroundCard {
        Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp)) {
            TextWithCaption(
                caption = "Дата",
                text = history.date.parseToShortString()
            )

            var counterValue by remember { mutableStateOf(0.0) }
            if (isSetHours) {
                Counter(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(100.dp),
                    value = counterValue,
                    step = 0.5,
                    valueChange = { counterValue = it }
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
                        hours = counterValue
                    }
                    description = tempDescription
                }
                viewModel.updateHistory(history)
            }) {
                Text(text = "Сохранить")
            }
        }
    }
}