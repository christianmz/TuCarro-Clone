package com.meazza.tucarro.ui.auth

interface AuthListener {
    fun onSuccess()
    fun onFailure(messageCode: Int)
}