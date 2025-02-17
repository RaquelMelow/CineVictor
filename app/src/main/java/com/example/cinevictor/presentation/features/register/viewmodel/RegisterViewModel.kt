package com.example.cinevictor.presentation.features.register.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cinevictor.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class RegisterViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _emailVerificationSent = MutableStateFlow(false)
    val emailVerificationSent: StateFlow<Boolean> = _emailVerificationSent

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _isEmailValid = MutableStateFlow(true)
    val isEmailValid: StateFlow<Boolean> = _isEmailValid

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword

    private val _doPasswordsMatch = MutableStateFlow(true)
    val doPasswordsMatch: StateFlow<Boolean> = _doPasswordsMatch

    private val _firstName = MutableStateFlow("")
    val firstName: StateFlow<String> = _firstName

    private val _lastName = MutableStateFlow("")
    val lastName: StateFlow<String> = _lastName

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    private val _birthDate = MutableStateFlow("")
    val birthDate: StateFlow<String> = _birthDate

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError

    private val _showDatePicker = MutableStateFlow(false)
    val showDatePicker: StateFlow<Boolean> = _showDatePicker

    private val _isPasswordValid = MutableStateFlow(true)
    val isPasswordValid: StateFlow<Boolean> = _isPasswordValid

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        _isEmailValid.value = newEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex())
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        _isPasswordValid.value = newPassword.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$".toRegex())
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        _confirmPassword.value = newConfirmPassword
        _doPasswordsMatch.value = _password.value == newConfirmPassword
    }

    fun onUserNameChange(newUserName: String) {
        _userName.value = newUserName
    }

    fun onDateSelected(newDate: String) {
        _birthDate.value = newDate
    }

    fun onShowDatePickerModal(show: Boolean) {
        _showDatePicker.value = show
    }

    fun onRegister() {
        if (isFormValid()) {
            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(_email.value, _password.value)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = task.result?.user
                        user?.let {
                            sendVerificationEmail(user)

                        }
                    } else {
                        _isError.value = true
                    }
                }
        } else {
            _isError.value = true
        }
    }

    private fun isFormValid(): Boolean {
        return _email.value.isNotEmpty() && _password.value.isNotEmpty() &&
                _password.value == _confirmPassword.value &&
                _firstName.value.isNotEmpty() && _lastName.value.isNotEmpty() &&
                _birthDate.value.isNotEmpty()
    }

    private fun sendVerificationEmail(user: FirebaseUser) {
        authRepository.sendVerificationEmail(user) { isSuccessful ->
            if (isSuccessful) {
                _emailVerificationSent.value = true
            } else {
                _emailVerificationSent.value = false
            }
        }
    }
}

