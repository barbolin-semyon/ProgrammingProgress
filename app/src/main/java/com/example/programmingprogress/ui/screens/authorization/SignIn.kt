package com.example.programmingprogress.ui.screens.authorization

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.programmingprogress.ui.navigation.AuthorizationScreen
import com.example.programmingprogress.util.checkValidEmail
import com.example.programmingprogress.viewmodel.AuthViewModel

@Composable
fun SignInView(navHostController: NavHostController) {
    val authViewModel = viewModel(AuthViewModel::class.java)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
                    text = "Введите пароль " + if (password.length in 1..5)
                        "(Меньше 6 символов)"
                    else ""
                )
            },
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                modifier = Modifier.padding(end = 8.dp),
                enabled = email.checkValidEmail() && password.length >= 6,
                onClick = { authViewModel.signInWithEmail(email, password) }
            ) {
                Text(text = "Войти", modifier = Modifier.padding(horizontal = 16.dp))
            }

            Button(
                onClick = {
                    navHostController.navigate(AuthorizationScreen.RegistrationScreen.route)
                }) {
                Text(text = "Зарегистрироваться")
            }


        }

    }


}