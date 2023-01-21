package com.example.programmingprogress.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.programmingprogress.R
import com.example.programmingprogress.ui.theme.DarkGray

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    value: Double,
    step: Double,
    valueChange: (newValue: Double) -> Unit
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    var newValue = value - step
                    if (newValue < 0) newValue = 0.0
                    valueChange(newValue)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_minus),
                    contentDescription = "minus hours",
                    tint = DarkGray
                )
            }

            Text(text = value.toString(), fontWeight = FontWeight.Bold, color = DarkGray)

            IconButton(
                onClick = { valueChange(value + step) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "plus hours"
                )
            }
        }
    }
}