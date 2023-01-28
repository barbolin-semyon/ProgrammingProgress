package com.example.programmingprogress.ui.screens.checkpoint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.programmingprogress.ui.theme.*
import com.example.programmingprogress.viewmodel.UserViewModel

@Composable
fun Checkpoint(navHostController: NavHostController) {
    val viewModel = viewModel(UserViewModel::class.java)

    val user = viewModel.userInfo.observeAsState()
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getInformationUser()
    })

    user.value?.let { user ->
        val count = user.countOfDaysSuccess / 5
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Alpha)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(count) { index ->
                Item(
                    number = (index + 1) * 5,
                    backgroundColor = Green,
                    textColor = White,
                    colorSpacer = Green,
                    isLast = (index + 1) == count
                )
            }

            if (count == 0) {
                item {
                    Item(
                        number = user.countOfDaysSuccess,
                        backgroundColor = Green,
                        textColor = White,
                        colorSpacer = Green,
                        isLast = true
                    )
                }
            } else {
                item {
                    Spacer(
                        modifier = Modifier
                            .width(10.dp)
                            .height(100.dp)
                            .background(brush = Brush.verticalGradient(listOf(Green, DarkGray)))
                    )
                }

                items(2) { index ->
                    Item(
                        number = (index + 2) * count * 5,
                        backgroundColor = Gray,
                        textColor = DarkGray,
                        colorSpacer = Color.DarkGray,
                        isLast = index == 1
                    )
                }
            }
        }
    }
}

@Composable
private fun Item(
    number: Int,
    backgroundColor: Color,
    textColor: Color,
    colorSpacer: Color,
    isLast: Boolean
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            backgroundColor = backgroundColor,
            shape = CircleShape,
            elevation = 8.dp,
            modifier = Modifier.size(100.dp),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "$number",
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 64.sp
                )
            }
        }

        if (isLast.not()) {
            Spacer(
                modifier = Modifier
                    .width(10.dp)
                    .height(100.dp)
                    .background(color = colorSpacer)
            )
        }
    }

}