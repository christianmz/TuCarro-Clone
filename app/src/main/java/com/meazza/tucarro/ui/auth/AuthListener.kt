package com.meazza.tucarro.ui.auth

interface AuthListener {
    fun onSuccess()
    fun onFailure(message: String)
}