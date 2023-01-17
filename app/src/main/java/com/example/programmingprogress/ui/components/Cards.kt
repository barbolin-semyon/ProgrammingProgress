package com.example.programmingprogress.ui.components

import android.util.Log
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.ui.theme.Blue
import com.example.programmingprogress.ui.theme.Gray
import com.example.programmingprogress.ui.theme.Red
import java.text.SimpleDateFormat
import java.util.*

enum class ClickType {
    LEFT_CLICK,
    RIGHT_CLICK
}

@Composable
fun BackgroundCard(
    topPadding: Dp,
    angleRound: Dp,
    rotation: Float = 0f,
    onClick: (isTapOnRight: Boolean) -> Unit = {},
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = topPadding)
            .graphicsLayer { rotationY = rotation }
            .pointerInput(Unit) {
                val maxWidth = this.size.width
                detectTapGestures(
                    onPress = { press ->
                        val isTapOnRight = ((press.x > (maxWidth / 4)))
                        onClick(isTapOnRight)
                    }
                )
            },
        shape = RoundedCornerShape(topEnd = angleRound),
        backgroundColor = Gray
    ) {
        content()
    }
}

@Composable
fun CardForHistory(history: History, rotation: Float, onClick: () -> Unit) {
    val format = SimpleDateFormat("dd MMMM yyyy", Locale("RU", "ru"))

    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .graphicsLayer(rotationY = rotation)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp)
    ) {
        Row {
            Text(
                text = format.format(history.getDate().time),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
                style = MaterialTheme.typography.body1
            )
            Spacer(
                modifier = Modifier
                    .size(16.dp)
                    .background(if (history.check) Blue else Red)
            )
        }
    }
}