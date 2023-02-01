package com.example.programmingprogress.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.programmingprogress.R
import com.example.programmingprogress.ui.theme.Green
import com.example.programmingprogress.ui.theme.Orange
import com.example.programmingprogress.ui.theme.White
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.diagram),
            contentDescription = "icon",
            modifier = Modifier.size(128.dp),
            tint = White
        )
        Text(text = "Your progress", style = MaterialTheme.typography.h3, color = White)
    }
}