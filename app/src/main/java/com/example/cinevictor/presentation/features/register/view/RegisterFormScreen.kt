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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinevictor.R
import com.example.cinevictor.presentation.features.register.viewmodel.RegisterViewModel
import com.example.cinevictor.presentation.ui.util.DatePickerModal
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun RegisterFormScreen(
    onRegisterSuccess: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val userName by viewModel.userName.collectAsState()
    val birthDate by viewModel.birthDate.collectAsState()
    val isError by viewModel.isError.collectAsState()
    val isEmailValid by viewModel.isEmailValid.collectAsState()
    val isPasswordValid by viewModel.isPasswordValid.collectAsState()
    val doPasswordsMatch by viewModel.doPasswordsMatch.collectAsState()
    val showDatePicker by viewModel.showDatePicker.collectAsState()

    val backgroundImage = painterResource(id = R.drawable.registercinevictorconlogo)

    Box(modifier = Modifier.fillMaxSize()) {
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
                onValueChange = { viewModel.onUserNameChange(it) },
                label = { Text("Nombre", color = Color.White) },
                isError = isError,
                colors = getTextFieldColors()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = lastName,
                onValueChange = { viewModel.onUserNameChange(it) },
                label = { Text("Apellidos", color = Color.White) },
                isError = isError,
                colors = getTextFieldColors()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = userName,
                onValueChange = { viewModel.onUserNameChange(it) },
                label = { Text("Username", color = Color.White) },
                isError = isError,
                colors = getTextFieldColors()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = { Text("Email", color = Color.White) },
                isError = !isEmailValid,
                colors = getTextFieldColors()
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

            Box(
                modifier = Modifier
                    .width(280.dp)
                    .height(56.dp)
                    .clickable { viewModel.onDateSelected("") }
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

            if (showDatePicker) {
                DatePickerModal(
                    onDateSelected = { selectedDateMillis ->
                        viewModel.onDateSelected(selectedDateMillis?.let {
                            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(it))
                        }.orEmpty())
                    },
                    onDismiss = { viewModel.onDateSelected("") }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text("Contraseña", color = Color.White) },
                isError = !isPasswordValid,
                visualTransformation = PasswordVisualTransformation(),
                colors = getTextFieldColors()
            )

            if (!isPasswordValid) {
                Text(
                    text = "Debe tener al menos 8 caracteres, una mayúscula, un número y un carácter especial.",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = confirmPassword,
                onValueChange = { viewModel.onConfirmPasswordChange(it) },
                label = { Text("Confirmar Contraseña", color = Color.White) },
                isError = !doPasswordsMatch,
                visualTransformation = PasswordVisualTransformation(),
                colors = getTextFieldColors()
            )

            if (!doPasswordsMatch) {
                Text(
                    text = "Las contraseñas no coinciden.",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.onRegister() },
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
}


@Preview(showBackground = true)
@Composable
fun RegisterFormScreenPreview() {
    RegisterFormScreen(onRegisterSuccess = {})
}
