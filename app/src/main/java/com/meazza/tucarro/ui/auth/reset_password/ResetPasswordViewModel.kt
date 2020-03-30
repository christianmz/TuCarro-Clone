package com.meazza.tucarro.ui.auth.reset_password

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.meazza.tucarro.repository.AuthRepository
import com.meazza.tucarro.ui.auth.AuthListener
import com.meazza.tucarro.util.EMPTY_FIELDS
import com.meazza.tucarro.util.INVALID_EMAIL
import com.meazza.tucarro.util.USER_NOT_FOUND
import com.meazza.tucarro.util.isValidEmail
import kotlinx.coroutines.launch

class ResetPasswordViewModel(private val authRepository: AuthRepository) : ViewModel() {

    var etEmailToResetPassword = MutableLiveData<String>()

    var authListener: AuthListener? = null

    fun btnResetPassword() {

        val email = etEmailToResetPassword.value

        viewModelScope.launch {
            if (!email.isNullOrEmpty()) {
                try {
                    when {
                        !isValidEmail(email) -> authListener?.onFailure(INVALID_EMAIL)
                        isValidEmail(email) -> {
                            authRepository.resetPassword(email)
                            authListener?.onSuccess()
                        }
                    }
                } catch (e: FirebaseAuthInvalidUserException) {
                    authListener?.onFailure(USER_NOT_FOUND)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                authListener?.onFailure(EMPTY_FIELDS)
            }
        }
    }
}
