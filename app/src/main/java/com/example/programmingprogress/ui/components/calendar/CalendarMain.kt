package com.example.programmingprogress.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.programmingprogress.R
import com.example.programmingprogress.ui.components.calendar.CalendarHeader
import com.example.programmingprogress.ui.components.calendar.CalendarItem
import com.example.programmingprogress.ui.theme.*
import com.example.programmingprogress.util.getDate
import com.example.programmingprogress.util.getDayOfWeek
import com.example.programmingprogress.util.getMonth
import java.text.SimpleDateFormat
import java.util.*


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarView(
    calendarTheme: CalendarTheme = CalendarTheme(),
    selectedDate: Calendar?,
    changeSelectedDate: (calendar: Calendar) -> Unit
) {
    var currentDate = remember { mutableStateOf(Calendar.getInstance()) }
    var days by remember { mutableStateOf(mutableListOf<Calendar>()) }
    val weeks = listOf("Пон", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(calendarTheme.backgroundColor)
    ) {
        CalendarHeader(
            currentDate = currentDate,
            theme = calendarTheme.calendarHeaderTheme
        )

        days = getDays(currentDate.value)
        LazyVerticalGrid(cells = GridCells.Fixed(7), content = {
            items(weeks) { weekDay ->
                Text(
                    weekDay,
                    color = calendarTheme.calendarHeaderTheme.headerTextColor,
                    textAlign = TextAlign.Center
                )
            }
            items(days) { day ->
                val date = selectedDate?.getDate() ?: 0
                val month = selectedDate?.getMonth() ?: 0
                val isSelected = date == day.getDate() && month == day.getMonth()

                CalendarItem(
                    day = day.getDate().toString(),
                    isSelected = isSelected,
                    calendarItemTheme = calendarTheme.calendarItemTheme
                ) {
                    changeSelectedDate(day)
                }
            }
        })
    }
}
private fun getDays(currentDate: Calendar): MutableList<Calendar> {

    val tempDate = currentDate.clone() as Calendar
    tempDate.set(Calendar.DATE, 1)

    var countDayBeforeCurrentMonth = tempDate.getDayOfWeek() - 1
    if (countDayBeforeCurrentMonth == 0) countDayBeforeCurrentMonth = 7
    tempDate.add(Calendar.DATE, 1 - countDayBeforeCurrentMonth)

    val tempDays = mutableListOf<Calendar>()
    val countDayInMonth =
        currentDate.getActualMaximum(Calendar.DAY_OF_MONTH) + countDayBeforeCurrentMonth - 1
    repeat(countDayInMonth) {
        tempDays.add(tempDate.clone() as Calendar)
        tempDate.add(Calendar.DATE, 1)
    }
    return tempDays
}