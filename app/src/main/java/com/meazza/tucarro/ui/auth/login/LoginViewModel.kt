package com.meazza.tucarro.ui.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.meazza.tucarro.network.AuthService
import com.meazza.tucarro.repository.AuthRepository
import com.meazza.tucarro.ui.ViewListener
import com.meazza.tucarro.ui.auth.AuthListener
import com.meazza.tucarro.util.*
import kotlinx.coroutines.launch


class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    var etEmail = MutableLiveData<String>()
    var etPassword = MutableLiveData<String>()

    var authListener: AuthListener? = null
    var viewListener: ViewListener? = null

    fun btnLogin() {

        val email = etEmail.value
        val password = etPassword.value

        viewModelScope.launch {
            if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                try {
                    authRepository.loginByEmail(email, password)
                    if (AuthService.currentUser!!.isEmailVerified) {
                        authListener?.onSuccess()
                    } else {
                        authListener?.onFailure(CONFIRM_YOUR_EMAIL)
                    }
                } catch (e: FirebaseAuthInvalidCredentialsException) {
                    authListener?.onFailure(WRONG_PASSWORD)
                } catch (e: FirebaseAuthInvalidUserException) {
                    authListener?.onFailure(USER_NOT_FOUND)
                } catch (e: Exception) {
                    authListener?.onFailure(LOGIN_ERROR)
                    e.printStackTrace()
                }
            } else {
                authListener?.onFailure(EMPTY_FIELDS)
            }
        }
    }

    fun btnGotoSignUp() {
        viewListener?.gotoActivity()
    }

    fun tvRecoverPassword() {
        viewListener?.openView()
    }
}