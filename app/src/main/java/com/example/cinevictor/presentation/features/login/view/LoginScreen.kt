package com.example.cinevictor.presentation.features.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinevictor.R
import com.example.cinevictor.presentation.features.login.viewmodel.LoginState
import com.example.cinevictor.presentation.features.login.viewmodel.LoginViewModel
import com.example.cinevictor.presentation.ui.util.SystemUiConfig
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginScreen(
    navigationToRegister: () -> Unit,
    navigationToHome: () -> Unit,
    viewModel: LoginViewModel = koinViewModel(),
    statusBarColor: Color = Color.Black,
    useDarkIcons: Boolean = false
) {
    val email by viewModel.email.collectAsState()
    val isEmailValid by viewModel.isEmailValid.collectAsState()
    val password by viewModel.password.collectAsState()
    val isError by viewModel.isError.collectAsState()
    val isLoginVisible by viewModel.isLoginVisible.collectAsState()
    val loginState by viewModel.loginState.collectAsState()

    val backgroundImage = painterResource(id = R.drawable.logincinevictorconlogo)

    SystemUiConfig(statusBarColor = statusBarColor, useDarkIcons = useDarkIcons)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp)
        ) {
            when (loginState) {
                is LoginState.Idle -> {
                    LoginContent(
                        email = email,
                        isEmailValid = isEmailValid,
                        password = password,
                        isError = isError,
                        isLoginVisible = isLoginVisible,
                        onEmailChange = { viewModel.onEmailChange(it) },
                        onPasswordChange = { viewModel.onPasswordChange(it) },
                        onToggleLoginVisibility = { viewModel.toggleLoginVisibility() },
                        onLoginClick = { viewModel.validateLogin() },
                        navigationToRegister = navigationToRegister
                    )
                }
                is LoginState.Loading -> {
                    Box {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.White
                        )
                    }
                }
                is LoginState.Success -> {
                    LaunchedEffect(Unit) {
                        navigationToHome()
                    }
                }
                is LoginState.EmailVerificationSent -> {
                    Text(
                        text = "Email de verificación enviado",
                        color = Color.Green,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                is LoginState.Error -> {
                    val errorMessage = (loginState as LoginState.Error).message
                    Text(
                        text = "Error: $errorMessage",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}
