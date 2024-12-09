package com.example.cinevictor.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun sendVerificationEmail(user: FirebaseUser, onComplete: (Boolean) -> Unit) {
        user.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("AuthRepository", "Email sent.")
                    onComplete(true)
                } else {
                    Log.w("AuthRepository", "Error sending email", task.exception)
                    onComplete(false)
                }
            }
    }
}
