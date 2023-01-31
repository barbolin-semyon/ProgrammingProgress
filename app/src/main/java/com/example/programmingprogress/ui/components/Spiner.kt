package com.example.programmingprogress.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programmingprogress.R
import com.example.programmingprogress.ui.screens.checkpoint.CheckpointCategory
import com.example.programmingprogress.ui.theme.DarkGray
import com.example.programmingprogress.ui.theme.Gray

@Composable
fun Spinner(
    items: List<CheckpointCategory>,
    hint: String,
    padding: PaddingValues = PaddingValues(),
    onClick: (text: CheckpointCategory) -> Unit,
) {
    val isExpand = remember { mutableStateOf(false) }

    Box() {
        Row(
            modifier = Modifier
                .padding(padding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = hint,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = DarkGray
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "to expand",
                Modifier
                    .height(25.dp)
                    .width(25.dp)
                    .clickable { isExpand.value = isExpand.value.not() },
                tint = DarkGray
            )
        }

        DropdownMenu(
            expanded = isExpand.value,
            onDismissRequest = { isExpand.value = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            for (item in items) {
                DropdownMenuItem(onClick = {
                    onClick(item)
                    isExpand.value = false
                }) {
                    Text(text = item.text, color = DarkGray)
                }
            }
        }
    }
}