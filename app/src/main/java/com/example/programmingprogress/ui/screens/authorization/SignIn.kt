package com.example.programmingprogress.ui.screens.authorization

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.programmingprogress.ui.components.CustomTextField
import com.example.programmingprogress.ui.navigation.AuthorizationScreen
import com.example.programmingprogress.ui.theme.Green
import com.example.programmingprogress.ui.theme.White
import com.example.programmingprogress.util.checkValidEmail
import com.example.programmingprogress.viewmodel.AuthViewModel

@Composable
fun SignInView(navHostController: NavHostController, authViewModel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            isError = email.checkValidEmail().not() && email.isNotEmpty(),
            textLabel = "Введите email",
        )

        CustomTextField(
            value = password,
            onValueChange = { password = it },
            textLabel = "Введите пароль "
        )

        Buttons(
            enabledSignIn = email.checkValidEmail(),
            onclickSignIn = { authViewModel.signInWithEmail(email, password) },
            onclickRegister = { navHostController.navigate(AuthorizationScreen.RegistrationScreen.route) }
        )
    }
}

@Composable
private fun Buttons(
    enabledSignIn: Boolean,
    onclickSignIn: () -> Unit,
    onclickRegister: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Green),
            modifier = Modifier.padding(end = 8.dp),
            enabled = enabledSignIn,
            onClick = { onclickSignIn() }
        ) {
            Text(
                text = "Войти",
                modifier = Modifier.padding(horizontal = 16.dp),
                color = White
            )
        }

        Button(
            onClick = { onclickRegister() }) {
            Text(text = "Зарегистрироваться")
        }
    }
}