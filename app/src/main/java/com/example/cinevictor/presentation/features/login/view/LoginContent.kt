package com.example.cinevictor.presentation.features.login.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun LoginContent(
    email: String,
    isEmailValid: Boolean,
    password: String,
    isError: Boolean,
    isLoginVisible: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onToggleLoginVisibility: () -> Unit,
    onLoginClick: () -> Unit,
    navigationToRegister: () -> Unit
) {
    if (!isLoginVisible) {
        Button(
            onClick = onToggleLoginVisibility,
            modifier = Modifier,
            shape = RectangleShape,
            colors = buttonColors(Color.White),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color.Black)
        ) {
            Text("Login", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "¿Aún no eres usuario de Cine Victor?",
                color = Color.White,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextButton(onClick = navigationToRegister) {
                Text("Registrarse", color = Color.Yellow)
            }
        }
    } else {
        TextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email", color = Color.White, fontSize = 14.sp) },
            modifier = Modifier
                .background(Color.Transparent),
            singleLine = true,
            shape = RectangleShape,
            isError = isError,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Black,
                unfocusedContainerColor = Color.Black,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                errorIndicatorColor = Color.Red
            )
        )

        if (!isEmailValid) {
            Text(
                text = "Por favor, introduce un correo electrónico válido.",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Contraseña", color = Color.White, fontSize = 14.sp) },
            modifier = Modifier.background(Color.Transparent),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            shape = RectangleShape,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Black,
                unfocusedContainerColor = Color.Black,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                errorIndicatorColor = Color.Red
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                shape = RectangleShape,
                colors = buttonColors(Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.Black)
            ) {
                Text("Login", color = Color.Black)
            }
        }

    }
}
