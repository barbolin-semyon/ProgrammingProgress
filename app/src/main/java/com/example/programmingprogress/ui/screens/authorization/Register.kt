package com.example.programmingprogress.ui.screens.authorization

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.programmingprogress.ui.components.CustomTextField
import com.example.programmingprogress.util.checkValidEmail
import com.example.programmingprogress.viewmodel.AuthViewModel

@Composable
fun RegisterView(authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }

    Column(Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        CustomTextField(
            value = nickname,
            onValueChange = { nickname = it },
            isError = password.length in 1..5,
            textLabel = "Введите nickname",
        )

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            isError = email.checkValidEmail().not() && email.isNotEmpty(),
            textLabel = "Введите email",
        )

        CustomTextField(
            value = password,
            onValueChange = { password = it },
            isError = password.length in 1..5,
            textLabel = "Введите пароль " + if (password.length in 1..5) "(Меньше 6 символов)"
            else ""
        )

        CustomTextField(
            value = repeatPassword,
            onValueChange = { repeatPassword = it },
            isError = password != repeatPassword,
            textLabel = "Повторите пароль" + if (password != repeatPassword) "(Пароли не совпадают)"
            else ""
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