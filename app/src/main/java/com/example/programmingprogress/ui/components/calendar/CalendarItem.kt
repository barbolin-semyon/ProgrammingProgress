package com.example.programmingprogress.ui.components.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.ui.theme.CalendarItemTheme
import com.example.programmingprogress.ui.theme.DarkGray
import com.example.programmingprogress.util.getDate
import com.example.programmingprogress.util.parseCalendar

@Composable
fun CalendarItem(
    day: History,
    calendarItemTheme: CalendarItemTheme,
    onClick: () -> Unit
) {
    calendarItemTheme.apply {

        Card(
            elevation = 16.dp,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.clickable { onClick() }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = day.date.parseCalendar().getDate().toString(),
                    style = textStyle,
                    fontSize = textSize,
                    color = DarkGray,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }
    }

}