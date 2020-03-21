package com.meazza.tucarro.ui.auth.sign_up

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.meazza.tucarro.R
import com.meazza.tucarro.databinding.ActivitySignUpBinding
import com.meazza.tucarro.ui.auth.AuthListener
import com.meazza.tucarro.util.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.longToast
import org.jetbrains.anko.okButton
import org.koin.android.ext.android.inject

class SignUpActivity : AppCompatActivity(),
    AuthListener {

    private val signUpViewModel by inject<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil
            .setContentView<ActivitySignUpBinding>(this, R.layout.activity_sign_up)
            .apply {
                lifecycleOwner = this@SignUpActivity
                viewModel = signUpViewModel
            }

        signUpViewModel.authListener = this

        setToolbar()
    }

    override fun onSuccess() {
        longToast(R.string.verification_email)
    }

    override fun onFailure(messageCode: Int) {
        when (messageCode) {
            EMPTY_FIELDS -> showAlert(resources.getString(R.string.empty_fields))
            INVALID_EMAIL -> showAlert(resources.getString(R.string.invalid_email))
            INVALID_PASSWORD -> showAlert(resources.getString(R.string.invalid_password))
            EMAIL_ALREADY_EXISTS -> showAlert(resources.getString(R.string.email_already_exists))
            REGISTRATION_ERROR -> showAlert(resources.getString(R.string.registration_error))
            EMAIL_NOT_SENT -> showAlert(resources.getString(R.string.email_hasnt_been_sent))
            else -> showAlert(resources.getString(R.string.error))
        }
    }

    private fun setToolbar() {
        setSupportActionBar(tb_sign_up)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showAlert(message: String) {
        alert(message) {
            okButton { it.dismiss() }
        }.show()
    }
}
