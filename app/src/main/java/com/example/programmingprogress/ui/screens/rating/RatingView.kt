package com.example.programmingprogress.ui.screens.rating

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.programmingprogress.ui.components.CardForRating
import com.example.programmingprogress.viewmodel.UserViewModel

@Composable
fun RatingView() {
    val viewModel = viewModel(UserViewModel::class.java)
    val users = viewModel.users.observeAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getUsersWithSortedByScore()
    })

    if (users.value?.isNotEmpty() == true) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            items(users.value!!.size) {index ->
                CardForRating(index = index, user = users.value!![index])
            }
        }
    }
}