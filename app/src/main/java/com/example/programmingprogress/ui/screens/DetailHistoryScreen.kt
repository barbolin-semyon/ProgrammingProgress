package com.example.programmingprogress.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.programmingprogress.R
import com.example.programmingprogress.model.History
import com.example.programmingprogress.ui.components.BackgroundCard
import com.example.programmingprogress.ui.components.CustomButtonFillSize
import com.example.programmingprogress.ui.components.CustomToolbarForHistory
import com.example.programmingprogress.ui.theme.Blue
import com.example.programmingprogress.ui.theme.DarkGray
import com.example.programmingprogress.ui.theme.Gray
import com.example.programmingprogress.ui.theme.Red

@Composable
fun DetailHistoryScreen(navHostController: NavHostController, history: History) {
    CustomToolbarForHistory(title = "Детали", navHostController = navHostController)

    BackgroundCard(topPadding = 90.dp, angleRound = 90.dp) {
        if (history.isCheck.not()) {
            NoProgrammingScreen(navHostController)
        } else {

        }
    }
}

@Composable
fun NoProgrammingScreen(navHostController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Сегодня вы не программировали",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp),
            color = DarkGray,
        )

        Image(
            painter = painterResource(id = R.drawable.panda),
            contentDescription = "panda",
            modifier = Modifier.padding(8.dp)
        )

        Column {
            CustomButtonFillSize(text = "Исправить", color = Blue) {

            }

            CustomButtonFillSize(color = Red, text = "Написать причину") {

            }
        }
    }

}
