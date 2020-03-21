package com.meazza.tucarro.ui.auth.sign_up

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.meazza.tucarro.repository.AuthRepository
import com.meazza.tucarro.ui.auth.AuthListener
import com.meazza.tucarro.util.*
import kotlinx.coroutines.launch

class SignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {

    var etNewName = MutableLiveData<String>()
    var etNewEmail = MutableLiveData<String>()
    var etNewPassword = MutableLiveData<String>()

    var authListener: AuthListener? = null

    fun btnSignUp() {

        val name = etNewName.value
        val email = etNewEmail.value
        val password = etNewPassword.value

        viewModelScope.launch {
            if (!name.isNullOrEmpty() && !email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                try {
                    when {
                        !isValidEmail(email) -> authListener?.onFailure(INVALID_EMAIL)
                        !isValidPassword(password) -> authListener?.onFailure(INVALID_PASSWORD)
                        isValidEmail(email) && isValidPassword(password) -> {
                            authRepository.signUpByEmail(email, password)
                            authRepository.sendVerificationEmail()
                            authListener?.onSuccess()
                        }
                    }
                } catch (e: FirebaseAuthUserCollisionException) {
                    authListener?.onFailure(EMAIL_ALREADY_EXISTS)
                } catch (e: Exception) {
                    authListener?.onFailure(REGISTRATION_ERROR)
                    e.printStackTrace()
                }
            } else {
                authListener?.onFailure(EMPTY_FIELDS)
            }
        }
    }
}