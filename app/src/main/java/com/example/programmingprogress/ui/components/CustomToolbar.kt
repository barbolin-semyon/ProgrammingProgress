package com.example.programmingprogress.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programmingprogress.R
import com.example.programmingprogress.ui.theme.White

@Composable
private fun CustomToolbarForHistory(title: String, openList: () -> Unit, openDiagram: () -> Unit) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(8.dp)) {
        Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = White)

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            IconButton(onClick = { openList() }) {
                Icon(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = "открыть календарь"
                )
            }

            IconButton(onClick = { openDiagram() }) {
                Icon(
                    painter = painterResource(id = R.drawable.diagram),
                    contentDescription = "открыть диаграммы"
                )
            }
        }
    }
}