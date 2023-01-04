package com.example.programmingprogress.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.programmingprogress.ui.theme.ProgrammingProgressTheme
import com.example.programmingprogress.ui.theme.White

@Composable
fun CustomButtonFillSize(color: Color, text: String, topPadding: Dp = 0.dp, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = topPadding, start = 8.dp, end = 8.dp)
    ) {
        Text(text = text, color = White)
    }
}