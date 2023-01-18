package com.example.programmingprogress.ui.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType.Companion.Sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programmingprogress.ui.theme.*
import com.example.programmingprogress.util.getDate
import com.example.programmingprogress.util.getDayOfWeek
import com.example.programmingprogress.util.getMonth
import com.example.programmingprogress.util.getYear
import java.text.SimpleDateFormat
import java.util.*


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun CalendarView(
    calendarTheme: CalendarTheme = CalendarTheme()
) {
    var currentDate by remember { mutableStateOf(Calendar.getInstance().apply {
        set(Calendar.MONTH, 10)
    })  }
    var selectedDate by remember { mutableStateOf<Calendar?>(null) }
    var days by remember { mutableStateOf(mutableListOf<Calendar>()) }
    val weeks = listOf("Пон", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(calendarTheme.backgroundColor)
    ) {
        CalendarHeader(
            header = SimpleDateFormat("LLLL, yyyy", Locale("ru")).format(currentDate.time),
            theme = calendarTheme.calendarHeaderTheme
        )

        days = getDays(currentDate)
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
                    selectedDate = day
                }
            }
        })
    }
}

@Composable
private fun CalendarHeader(
    header: String,
    theme: CalendarHeaderTheme
) {
    theme.apply {
        Text(
            text = header.uppercase(),
            style = headerTextStyle,
            fontSize = headerTextSize,
            fontWeight = headerTextWeight,
            color = headerTextColor,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
private fun CalendarItem(
    day: String,
    isSelected: Boolean,
    calendarItemTheme: CalendarItemTheme,
    onClick: () -> Unit
) {
    calendarItemTheme.apply {
        val backgroundColor = if (isSelected) selectedDayBackgroundColor else dayBackgroundColor
        val textColor = if (isSelected) selectedDayValueTextColor else dayValueTextColor

        Column(
            modifier = Modifier
                .clip(dayShape)
                .background(backgroundColor)
                .clickable { onClick() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = day,
                style = textStyle,
                fontSize = textSize,
                color = textColor,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }

}

private fun getDays(currentDate: Calendar): MutableList<Calendar> {

    val tempDate = currentDate.clone() as Calendar
    tempDate.set(Calendar.DATE, 1)

    var countDayBeforeCurrentMonth = tempDate.getDayOfWeek() - 1
    if (countDayBeforeCurrentMonth == 0) countDayBeforeCurrentMonth = 7
    tempDate.add(Calendar.DATE, 1 - countDayBeforeCurrentMonth)

    val tempDays = mutableListOf<Calendar>()
    val countDayInMonth = currentDate.getActualMaximum(Calendar.DAY_OF_MONTH) + countDayBeforeCurrentMonth - 1
    repeat(countDayInMonth) {
        tempDays.add(tempDate.clone() as Calendar)
        tempDate.add(Calendar.DATE, 1)
    }
    return tempDays
}