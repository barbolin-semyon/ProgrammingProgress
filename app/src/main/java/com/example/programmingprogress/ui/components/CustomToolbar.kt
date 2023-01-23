package com.example.programmingprogress.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.programmingprogress.R
import com.example.programmingprogress.ui.theme.White

@Composable
fun CustomToolbar(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h4,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun CustomToolbarWithCalendar(
    title: String,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.h4)

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            IconButton(onClick = { onClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = "открыть календарь",
                    tint = White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}