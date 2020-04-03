package com.meazza.tucarro.repository

import com.meazza.tucarro.network.AuthService

class MainRepository(authService: AuthService) {

    val user = authService.currentUser
    val signOut = authService.signOut
}