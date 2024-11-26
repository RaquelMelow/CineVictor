package com.example.cinevictor.presentation.features.register.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinevictor.R
import com.example.cinevictor.presentation.ui.util.DatePickerModal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun RegisterFormScreen(onRegisterSuccess: () -> Unit) {

    var email by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordValid by remember { mutableStateOf(true) }
    var doPasswordsMatch by remember { mutableStateOf(true) }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var birthDate by remember { mutableStateOf("") }

    val backgroundImage = painterResource(id = R.drawable.registercinevictorconlogo)

    val counterMaxLength = 20
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    val passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$".toRegex()



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
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = {
                    Text(
                        "Nombre",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                ) },
                modifier = Modifier.background(Color.Transparent),
                singleLine = true,
                shape = RectangleShape,
                isError = isError,
                colors = getTextFieldColors()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = {
                    Text(
                    "Apellidos",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier
                    .background(Color.Transparent),
                singleLine = true,
                shape = RectangleShape,
                isError = isError,
                colors = getTextFieldColors()

            )

            Spacer(modifier = Modifier.height(8.dp))

            //user
            Column {
                TextField(
                    value = userName,
                    onValueChange = {
                        if (it.length <= counterMaxLength) {
                            userName = it
                        }
                    },
                    singleLine = true,
                    label = {
                        Text(
                            text = "Username",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    modifier = Modifier
                        .background(Color.Transparent),
                    shape = RectangleShape,
                    isError = isError,
                    colors = getTextFieldColors()
                )
                Text(
                    text = "${userName.length}/$counterMaxLength",
                    color = if (isError) Color.Red else Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = email,
                onValueChange = {
                    email = it
                    isEmailValid = emailRegex.matches(it)
                },
                label = {
                    Text(
                        "Email",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier.background(Color.Transparent),
                singleLine = true,
                shape = RectangleShape,
                isError = !isEmailValid,
                colors = getTextFieldColors()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .width(280.dp)
                    .height(56.dp)
                    .clickable { showDatePicker = true }
                    .border(
                        width = 1.dp,
                        color = if (isError && birthDate.isEmpty()) Color.Red else Color.Gray,
                        shape = RectangleShape
                    )
                    .background(Color.Black)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    if (birthDate.isEmpty()) {
                        Text(
                            text = "Fecha de Nacimiento",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        Text(
                            text = birthDate,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Seleccionar fecha",
                        tint = Color.White
                    )
                }
            }


            TextField(
                value = password,
                onValueChange = {
                    password = it
                    isPasswordValid = passwordRegex.matches(it)
                },
                label = {
                    Text(
                        "Contraseña",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier
                    .background(Color.Transparent),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                shape = RectangleShape,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                isError = !isPasswordValid,
                colors = getTextFieldColors()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    doPasswordsMatch = password == it
                },
                label = {
                    Text(
                        "Confirmar Contraseña",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier
                    .background(Color.Transparent),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                shape = RectangleShape,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                isError = isError,
                colors = getTextFieldColors()
            )

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword &&
                        firstName.isNotEmpty() && lastName.isNotEmpty() && birthDate.isNotEmpty()
                    ) {
                        onRegisterSuccess()
                    } else {
                        isError = true
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.White),
                border = BorderStroke(1.dp, Color.Gray)
            ) {
                Text("Registrarse", fontSize = 16.sp, color = Color.Black)
            }
        }
    }

    if (!isEmailValid) {
        Text(
            text = "Por favor, introduce un correo electrónico válido.",
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )
    }

    if (showDatePicker) {
        DatePickerModal(
            onDateSelected = { selectedDateMillis ->
                if (selectedDateMillis != null) {
                    val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        .format(Date(selectedDateMillis))
                    birthDate = formattedDate
                }
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false }
        )
    }

    if (!isPasswordValid) {
        Text(
            text = "Debe tener al menos 8 caracteres, una mayúscula, un número y un carácter especial.",
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )
    }

    if (!doPasswordsMatch) {
        Text(
            text = "Las contraseñas no coinciden.",
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterFormScreenPreview() {
    RegisterFormScreen(onRegisterSuccess = {})
}
