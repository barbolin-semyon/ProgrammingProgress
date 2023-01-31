package com.example.programmingprogress.ui.screens.checkpoint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.programmingprogress.ui.components.Item
import com.example.programmingprogress.ui.theme.*

@Composable
fun CheckpointViewForHoursOrDay(count: Int) {
    val cutCount = count / 5

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Alpha)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(cutCount) { index ->
            Item(
                number = (index + 1) * 5,
                backgroundColor = Green,
                textColor = White,
                colorSpacer = Green,
                isLast = (index + 1) == count
            )
        }

        if (cutCount == 0) {
            item {
                Item(
                    number = count,
                    backgroundColor = Green,
                    textColor = White,
                    colorSpacer = Green,
                    isLast = true
                )
            }
        } else {
            item {
                Spacer(
                    modifier = Modifier
                        .width(10.dp)
                        .height(100.dp)
                        .background(brush = Brush.verticalGradient(listOf(Green, DarkGray)))
                )
            }

            items(2) { index ->
                Item(
                    number = (index + 2) * count * 5,
                    backgroundColor = Gray,
                    textColor = DarkGray,
                    colorSpacer = Color.DarkGray,
                    isLast = index == 1
                )
            }
        }
    }
}