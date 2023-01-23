package com.example.programmingprogress.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

enum class ClickType {
    LEFT_CLICK,
    RIGHT_CLICK
}

@Composable
fun BackgroundCard(
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),
        shape = RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp),
        backgroundColor = Gray
    ) {
        content()
    }
}
private fun getColorForHistory(history: History): Color {
    if (history.check) return Green
    else if (history.description.isNotEmpty()) return Orange
    return Red

}