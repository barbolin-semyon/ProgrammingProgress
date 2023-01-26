package com.example.programmingprogress.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.programmingprogress.ui.theme.Green

@Composable
fun CustomTextField(
    textLabel: String,
    value: String,
    onValueChange: (value: String) -> Unit,
    isError: Boolean = false
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Green,
            focusedLabelColor = Green
        ),
        onValueChange = { onValueChange(it) },
        isError = isError,
        label = {
            Text(text = textLabel)
        },
    )
}