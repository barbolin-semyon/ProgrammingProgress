package com.example.programmingprogress.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.ui.components.calendar.CalendarHeader
import com.example.programmingprogress.ui.components.calendar.CalendarItem
import com.example.programmingprogress.ui.theme.*
import com.example.programmingprogress.util.*
import java.util.*


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarView(
    calendarTheme: CalendarTheme = CalendarTheme(),
    days: List<History>,
    onClick: (history: History) -> Unit,
    changeMonth: (index: Int) -> Unit
) {
    val weeks = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(calendarTheme.backgroundColor)
    ) {
        CalendarHeader(
            currentDate = days[15].date,
            theme = calendarTheme.calendarHeaderTheme,
            changeMonth = {
                changeMonth(it)
            }
        )

        LazyVerticalGrid(cells = GridCells.Fixed(7), content = {
            items(weeks) { weekDay ->
                Text(
                    weekDay,
                    color = calendarTheme.calendarHeaderTheme.headerTextColor,
                    textAlign = TextAlign.Center
                )
            }
            items(days) { day ->

                CalendarItem(
                    day = day,
                    calendarItemTheme = calendarTheme.calendarItemTheme
                ) {
                    onClick(day)
                }
            }
        })
    }
}