package com.example.programmingprogress.ui.screens.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.ui.components.BackgroundCard
import com.example.programmingprogress.ui.components.Counter
import com.example.programmingprogress.ui.components.TextWithCaption
import com.example.programmingprogress.util.parseToShortString

@Composable
fun InputHistoryScreen(
    navHostController: NavHostController,
    history: History,
    isSetHours: Boolean
) {
    BackgroundCard(topPadding = 90.dp, angleRound = 90.dp) {
        Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp)) {
            TextWithCaption(
                caption = "Дата",
                text = history.getDate().parseToShortString()
            )

            var counterValue by remember { mutableStateOf(0.0) }

            if (isSetHours) {
                Counter(
                    modifier = Modifier.padding(horizontal = 8.dp).height(100.dp),
                    value = counterValue,
                    step = 0.5,
                    valueChange = { counterValue = it }
                )
            }
        }
    }
}