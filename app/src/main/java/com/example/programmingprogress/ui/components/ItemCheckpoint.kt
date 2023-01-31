package com.example.programmingprogress.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Item(
    number: Int,
    backgroundColor: Color,
    textColor: Color,
    colorSpacer: Color,
    isLast: Boolean
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            backgroundColor = backgroundColor,
            shape = CircleShape,
            elevation = 8.dp,
            modifier = Modifier.size(100.dp),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "$number",
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 64.sp
                )
            }
        }

        if (isLast.not()) {
            Spacer(
                modifier = Modifier
                    .width(10.dp)
                    .height(100.dp)
                    .background(color = colorSpacer)
            )
        }
    }
}