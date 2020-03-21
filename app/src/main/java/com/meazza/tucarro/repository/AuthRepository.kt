package com.meazza.tucarro.repository

import com.meazza.tucarro.network.AuthService

class AuthRepository(private val authService: AuthService) {

    suspend fun signUpByEmail(email: String, password: String) {
        authService.signUpByEmail(email, password)
    }

    suspend fun loginByEmail(email: String, password: String) {
        authService.loginByEmail(email, password)
    }

    fun sendVerificationEmail() {
        authService.sendVerificationEmail()
    }

    suspend fun resetPassword(email: String) {
        authService.resetPassword(email)
    }
}