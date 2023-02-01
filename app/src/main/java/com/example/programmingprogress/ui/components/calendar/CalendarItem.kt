package com.example.programmingprogress.ui.components.calendar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.ui.theme.*
import com.example.programmingprogress.util.getDate
import com.example.programmingprogress.util.parseCalendar
import java.util.*

@Composable
fun CalendarItem(
    day: History,
    calendarItemTheme: CalendarItemTheme,
    onClick: () -> Unit
) {
    calendarItemTheme.apply {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 8.dp).clickable { onClick() }
        ) {
            Text(
                text = day.date.parseCalendar().getDate().toString(),
                fontSize = 18.sp,
                color = DarkGray,
            )

            val color =
                if (day.check) Green
                else if (day.description.isNotEmpty()) Orange
                else Red

            if (day.date.time < Calendar.getInstance().time.time) {
                Canvas(
                    modifier = Modifier
                        .size(12.dp)
                        .padding(vertical = 4.dp),
                    onDraw = {
                        drawCircle(color, this.size.height)
                    }
                )
            }

        }
    }

}