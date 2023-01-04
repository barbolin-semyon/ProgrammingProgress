package com.example.programmingprogress.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.programmingprogress.ui.theme.Gray

@Composable
fun BackgroundCard(topPadding: Dp, angleRound: Dp, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxSize().padding(top = topPadding),
        shape = RoundedCornerShape(topEnd = angleRound),
        backgroundColor = Gray
    ) {
        content()
    }
}