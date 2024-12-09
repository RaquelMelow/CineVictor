package com.example.cinevictor.presentation.features.login.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cinevictor.data.repository.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {

    private val auth = Firebase.auth
    private val authRepository = AuthRepository()

    private val _emailVerificationSent = MutableStateFlow(false)
    val emailVerificationSent: StateFlow<Boolean> = _emailVerificationSent

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _isEmailValid = MutableStateFlow(true)
    val isEmailValid: StateFlow<Boolean> = _isEmailValid

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _isError = MutableStateFlow(false)
    val isError = _isError.asStateFlow()

    private val _isLoginVisible = MutableStateFlow(false)
    val isLoginVisible = _isLoginVisible.asStateFlow()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        _isEmailValid.value = newEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex())
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun toggleLoginVisibility() {
        _isLoginVisible.value = !_isLoginVisible.value
    }

    fun validateLogin() {
        if (_email.value.isNotEmpty() && _password.value.isNotEmpty()) {
            _loginState.value = LoginState.Loading
            auth.signInWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        if (user != null && !user.isEmailVerified) {
                            sendVerificationEmail(user)
                        } else {
                            _loginState.value = LoginState.Success(user)
                        }
                    } else {
                        _loginState.value = LoginState.Error(task.exception?.localizedMessage ?: "Unknown error")
                    }
                }
        } else {
            _isError.value = true
        }
    }

    private fun sendVerificationEmail(user: com.google.firebase.auth.FirebaseUser?) {
        if (user != null) {
            authRepository.sendVerificationEmail(user) { isSuccessful ->
                if (isSuccessful) {
                    _loginState.value = LoginState.EmailVerificationSent
                } else {
                    _loginState.value = LoginState.Error("Failed to send verification email")
                }
            }
        }
    }
}