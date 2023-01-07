package com.example.programmingprogress.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.programmingprogress.R
import com.example.programmingprogress.ui.navigation.HistoryScreen
import com.example.programmingprogress.ui.theme.White

@Composable
fun CustomToolbarForHistory(title: String, navHostController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.h4)

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            IconButton(onClick = {
                navHostController.navigate(HistoryScreen.ListHistoryScreen.route)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = "открыть календарь",
                    tint = White,
                    modifier = Modifier.size(32.dp)
                )
            }

            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.diagram),
                    contentDescription = "открыть диаграммы",
                    tint = White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}