package com.example.programmingprogress.ui.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.programmingprogress.ui.theme.CalendarItemTheme

@Composable
fun CalendarItem(
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