package com.meazza.tucarro.repository

import com.meazza.tucarro.model.User
import com.meazza.tucarro.network.AuthService
import com.meazza.tucarro.network.DatabaseConnection

class AuthRepository(
    private val authService: AuthService,
    private val database: DatabaseConnection
) {

    suspend fun signUpByEmail(email: String, password: String) {
        authService.signUpByEmail(email, password)
    }

    suspend fun addUser(user: User) {
        database.addUser(user)
    }

    suspend fun loginByEmail(email: String, password: String) {
        authService.loginByEmail(email, password)
    }

    suspend fun resetPassword(email: String) {
        authService.resetPassword(email)
    }

    /*fun sendVerificationEmail() {
        authService.sendVerificationEmail()
    }*/
}