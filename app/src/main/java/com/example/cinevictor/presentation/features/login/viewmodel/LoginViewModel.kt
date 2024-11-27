package com.example.cinevictor.presentation.features.login.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _isError = MutableStateFlow(false)
    val isError = _isError.asStateFlow()

    private val _isLoginVisible = MutableStateFlow(false)
    val isLoginVisible = _isLoginVisible.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun toggleLoginVisibility() {
        _isLoginVisible.value = !_isLoginVisible.value
    }

    fun validateLogin(onLoginSuccess: () -> Unit) {
        if (_email.value.isNotEmpty() && _password.value.isNotEmpty()) {
            onLoginSuccess()
        } else {
            _isError.value = true
        }
    }

}