package com.example.programmingprogress.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextWithCaption(caption: String, text: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(text = caption, style = MaterialTheme.typography.h5)
        Text(text = text, style = MaterialTheme.typography.body1)
    }
}