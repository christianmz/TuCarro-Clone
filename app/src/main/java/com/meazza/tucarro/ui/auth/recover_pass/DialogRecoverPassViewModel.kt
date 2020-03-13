package com.meazza.tucarro.ui.auth.recover_pass

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.meazza.tucarro.repository.AuthRepository
import com.meazza.tucarro.ui.auth.AuthListener

class DialogRecoverPassViewModel(repository: AuthRepository) : ViewModel() {

    val etRecoverPassword = liveData<String> {}

    var listener: AuthListener? = null

    fun btnRecoverPassword() {
    }
}
