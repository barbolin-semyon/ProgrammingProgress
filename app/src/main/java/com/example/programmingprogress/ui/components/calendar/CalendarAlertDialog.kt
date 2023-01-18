package com.example.programmingprogress.ui.components.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.programmingprogress.ui.components.CalendarView
import com.example.programmingprogress.ui.theme.CalendarAlertDialogTheme
import java.util.*

@Composable
fun CalendarAlertDialog(
    CalendarAlertDialogTheme: CalendarAlertDialogTheme = CalendarAlertDialogTheme(),
    selectedDate: Calendar?,
    changeSelectedDate: (calendar: Calendar?) -> Unit
) {
    val isVisible = remember { mutableStateOf(true) }

    if (isVisible.value) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable { isVisible.value = false },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card {
                Column(modifier = Modifier.clickable {  }) {
                    CalendarView(
                        selectedDate = selectedDate,
                        changeSelectedDate = { changeSelectedDate(it) })

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            modifier = Modifier.width(100.dp),
                            enabled = selectedDate != null,
                            onClick = { isVisible.value = false }
                        ) {
                            Text(text = "Ок")
                        }

                        Button(
                            modifier = Modifier.padding(horizontal = 8.dp).width(100.dp),
                            onClick = {
                            changeSelectedDate(null)
                            isVisible.value = false
                        }) {
                            Text(text = "Отмена")
                        }
                    }
                }
            }
        }
    }
}