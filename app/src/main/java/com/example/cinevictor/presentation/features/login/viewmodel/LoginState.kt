package com.example.cinevictor.presentation.features.login.viewmodel

import com.google.firebase.auth.FirebaseUser

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object EmailVerificationSent : LoginState()
    data class Success(val user: FirebaseUser?) : LoginState()
    data class Error(val message: String) : LoginState()
}
