package com.example.programmingprogress.ui.screens.authorization

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.programmingprogress.util.checkValidEmail
import com.example.programmingprogress.viewmodel.AuthViewModel

@Composable
fun RegisterView(authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }

    Column(Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nickname,
            onValueChange = { nickname = it },
            isError = password.length in 1..5,
            label = { Text(text = "Введите nickname") },
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            isError = email.checkValidEmail().not() && email.isNotEmpty(),
            label = { Text(text = "Введите email") },
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            isError = password.length in 1..5,
            label = {
                Text(
                    text = "Введите пароль " + if (password.length in 1..5) "(Меньше 6 символов)"
                    else ""
                )
            },
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = repeatPassword,
            onValueChange = { repeatPassword = it },
            isError = password != repeatPassword,
            label = {
                Text(
                    text = "Повторите пароль" + if (password != repeatPassword) "(Пароли не совпадают)"
                    else ""
                )
            },
        )

        Button(
            enabled = email.checkValidEmail()
                    && password.length >= 6
                    && password == repeatPassword
                    && nickname.isNotEmpty(),
            onClick = {
                authViewModel.register(
                    email = email,
                    nickname = nickname,
                    password = password
                )
            }) {
            Text(text = "Зарегистрироваться")
        }
    }

}