package com.example.cinevictor.presentation.features.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinevictor.R

private val textFieldColors @Composable get() = TextFieldDefaults.colors(
    focusedContainerColor = Color.Black,
    unfocusedContainerColor = Color.Black,
    errorContainerColor = Color.Black,
    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White,
    errorTextColor = Color.Red,
    focusedIndicatorColor = Color.White,
    unfocusedIndicatorColor = Color.Gray,
    errorIndicatorColor = Color.Red,
    cursorColor = Color.White,
    focusedLabelColor = Color.White,
    unfocusedLabelColor = Color.Gray,
    errorLabelColor = Color.Red
)

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var isLoginVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    val backgroundImage = painterResource(id = R.drawable.logincinevictorconlogo)



    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = "Fondo",
            modifier = Modifier.fillMaxSize()
        )

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp)
        ) {
            if (!isLoginVisible) {
                Button(
                    onClick = { isLoginVisible = true },
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
                    TextButton(onClick = { /* Acción de registro */ }) {
                        Text("Registrarse", color = Color.Yellow)
                    }
                }
            } else {
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email", color = Color.White, fontSize = 14.sp) },
                    modifier = Modifier
                        .background(Color.Transparent),
                    singleLine = true,
                    shape = RectangleShape,
                    isError = isError,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Black,
                        unfocusedContainerColor = Color.Black,
                        errorContainerColor = Color.Black,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        errorTextColor = Color.Red,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.Gray,
                        errorIndicatorColor = Color.Red,
                        cursorColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña", color = Color.White, fontSize = 14.sp) },
                    modifier = Modifier
                        .background(Color.Transparent),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RectangleShape,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    isError = isError,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Black,
                        unfocusedContainerColor = Color.Black,
                        errorContainerColor = Color.Black,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        errorTextColor = Color.Red,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.Gray,
                        errorIndicatorColor = Color.Red,
                        cursorColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (email.isNotEmpty() && password.isNotEmpty()) {
                            onLoginSuccess()
                        } else {
                            isError = true
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp),
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
                    TextButton(onClick = { /* Acción de registro */ }) {
                        Text("Registrarse", color = Color.Yellow)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginSuccess = { })
}

