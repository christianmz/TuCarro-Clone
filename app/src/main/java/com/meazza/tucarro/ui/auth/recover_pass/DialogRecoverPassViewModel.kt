package com.meazza.tucarro.ui.auth.recover_pass

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.meazza.tucarro.repository.AuthRepository
import com.meazza.tucarro.ui.auth.AuthListener
import com.meazza.tucarro.util.INVALID_EMAIL
import com.meazza.tucarro.util.USER_NOT_FOUND
import kotlinx.coroutines.launch

class DialogRecoverPassViewModel(private val authRepository: AuthRepository) : ViewModel() {

    var etRecoverPassword = MutableLiveData<String>()

    var authListener: AuthListener? = null

    fun btnRecoverPassword() {

        val email = etRecoverPassword.value

        viewModelScope.launch {

            if (!email.isNullOrEmpty()) {
                try {
                    authRepository.resetPassword(email)
                    authListener?.onSuccess()
                } catch (e: FirebaseAuthInvalidUserException) {
                    authListener?.onFailure(USER_NOT_FOUND)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                authListener?.onFailure(INVALID_EMAIL)
            }
        }
    }
}
