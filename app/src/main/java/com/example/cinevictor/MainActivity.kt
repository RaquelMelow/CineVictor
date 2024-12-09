package com.example.cinevictor

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.cinevictor.presentation.navigation.AppNavHost
import com.example.cinevictor.presentation.ui.theme.CineTemita
import com.google.firebase.Firebase
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent(){
            CineTemita {
                Scaffold(Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(Modifier.padding(innerPadding))

                    val auth = Firebase.auth
                    val user = auth.currentUser!!

                    val url = "http://www.example.com/verify?uid=" + user.uid
                    val actionCodeSettings = ActionCodeSettings.newBuilder()
                        .setUrl(url)
                        .setIOSBundleId("com.example.ios")
                        .setAndroidPackageName("com.example.android", false, null)
                        .build()

                    user.sendEmailVerification(actionCodeSettings)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d(TAG, "Email sent.")
                            }
                        }
                }
            }
        }
    }
}

