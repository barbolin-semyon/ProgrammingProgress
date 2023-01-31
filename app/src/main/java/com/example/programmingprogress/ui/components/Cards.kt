package com.example.programmingprogress.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.model.entities.User
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

@Composable
fun CardForRating(index: Int, user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        elevation = 16.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                TextWithCaption(
                    caption = "name", text = user.name
                )

                TextWithCaption(
                    caption = "Score",
                    text = "${user.countOfConsecutiveDaysSuccess * 3 + user.countOfDaysSuccess + user.hours}"
                )
            }

            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "${index + 1}",
                color = Green,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}