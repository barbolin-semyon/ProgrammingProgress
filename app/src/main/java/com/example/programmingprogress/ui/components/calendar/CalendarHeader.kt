package com.example.programmingprogress.ui.components.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.programmingprogress.R
import com.example.programmingprogress.ui.theme.CalendarHeaderTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CalendarHeader(
    changeMonth: (index: Int) -> Unit,
    currentDate: Date,
    theme: CalendarHeaderTheme,
) {
    val header = SimpleDateFormat("LLLL, yyyy", Locale("ru")).format(currentDate.time)

    theme.apply {

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            ButtonForChangeCurrentMonth(
                changeMonth = {changeMonth(-1)},
                iconId = R.drawable.baseline_arrow_left,
                color = headerTextColor,
            )

            Text(
                text = header.uppercase(),
                style = headerTextStyle,
                fontSize = headerTextSize,
                fontWeight = headerTextWeight,
                color = headerTextColor,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            ButtonForChangeCurrentMonth(
                changeMonth = {changeMonth(1)},
                iconId = R.drawable.baseline_arrow_right,
                color = headerTextColor,
            )
        }
    }
}

@Composable
private fun ButtonForChangeCurrentMonth(
    changeMonth: () -> Unit,
    iconId: Int,
    color: Color,
) {
    Icon(
        painter = painterResource(id = iconId),
        contentDescription = "change month",
        tint = color,
        modifier = Modifier
            .size(70.dp)
            .clickable { changeMonth() }
    )
}