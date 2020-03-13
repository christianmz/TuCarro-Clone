package com.meazza.tucarro.ui.auth.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.meazza.tucarro.repository.AuthRepository
import com.meazza.tucarro.ui.auth.AuthListener

class SignUpViewModel(repository: AuthRepository) : ViewModel() {

    val etNewName = liveData<String> {}
    val etNewEmail = liveData<String> {}
    val etNewPassword = liveData<String> {}

    var listener: AuthListener? = null

    fun btnSignUp() {

    }
}