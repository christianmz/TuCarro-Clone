package com.meazza.tucarro.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.meazza.tucarro.repository.AuthRepository
import com.meazza.tucarro.ui.ViewListener
import com.meazza.tucarro.ui.auth.AuthListener


class LoginViewModel(repository: AuthRepository) : ViewModel() {

    val etUser = liveData<String> {}
    val etPassword = liveData<String> {}

    var authListener: AuthListener? = null
    var viewListener: ViewListener? = null

    fun btnLogin() {

    }

    fun btnGotoSignUp() {
        viewListener?.gotoActivity()
    }

    fun tvRecoverPassword() {
        viewListener?.openView()
    }
}