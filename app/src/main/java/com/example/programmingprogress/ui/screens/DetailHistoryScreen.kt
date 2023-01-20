package com.example.programmingprogress.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.programmingprogress.R
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.ui.components.BackgroundCard
import com.example.programmingprogress.ui.components.CustomButtonFillSize
import com.example.programmingprogress.ui.components.CustomToolbarForHistory
import com.example.programmingprogress.ui.components.TextWithCaption
import com.example.programmingprogress.ui.navigation.HistoryScreen
import com.example.programmingprogress.ui.theme.DarkGray
import com.example.programmingprogress.ui.theme.Green
import com.example.programmingprogress.ui.theme.Red
import com.example.programmingprogress.util.parseToShortString

@Composable
fun DetailHistoryScreen(navHostController: NavHostController, history: History) {
    CustomToolbarForHistory(title = "Детали") {
        navHostController.navigate(HistoryScreen.ListHistoryScreen.route) {

        }
    }

    BackgroundCard(topPadding = 90.dp, angleRound = 90.dp) {
        if (history.check.not()) {
            NoProgrammingScreen(navHostController)
        } else {
            Content(history)
        }
    }
}

@Composable
private fun Content(history: History) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(Modifier.padding(start = 16.dp)) {
            TextWithCaption(
                caption = "Время",
                text = "${history.hours}",
            )

            TextWithCaption(
                caption = "Дата",
                text = history.getDate().parseToShortString(),
            )
        }


        Card(elevation = 4.dp, shape = RoundedCornerShape(32.dp)){
            Icon(
                painter = painterResource(id = R.drawable.checkpoint),
                contentDescription = "is program",
                tint = Green,
                modifier = Modifier
                    .size(350.dp)
                    .padding(16.dp)
            )
        }

        Card(modifier = Modifier.padding(16.dp), elevation = 8.dp) {

            Text(modifier = Modifier.padding(16.dp), text = history.description, fontSize = 18.sp)
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
            modifier = Modifier.fillMaxWidth()
        )

        Column {
            CustomButtonFillSize(text = "Исправить", color = Green) {

            }

            CustomButtonFillSize(color = Red, text = "Написать причину") {

            }
        }
    }

}
