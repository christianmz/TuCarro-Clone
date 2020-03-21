package com.meazza.tucarro.network

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

object AuthService {

    private val mAuth by lazy { FirebaseAuth.getInstance() }

    val currentUser = mAuth.currentUser
    val signOut = mAuth.signOut()

    suspend fun signUpByEmail(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun loginByEmail(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).await()
    }

    fun sendVerificationEmail() {
        currentUser?.sendEmailVerification()?.addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("EMAIL", "EMAIL HAS BEEN SENT")
            } else {
                Log.d("EMAIL", "EMAIL HASN'T BEEN SENT")
            }
        }
    }

    suspend fun resetPassword(email: String) {
        mAuth.sendPasswordResetEmail(email).await()
    }
}