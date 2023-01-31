package com.example.programmingprogress.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.ui.theme.*

@Composable
fun BackgroundCard(
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp),
        shape = RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp),
        backgroundColor = Gray
    ) {
        content()
    }
}