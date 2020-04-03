package com.meazza.tucarro.ui.auth.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.meazza.tucarro.R
import com.meazza.tucarro.databinding.ActivityLoginBinding
import com.meazza.tucarro.ui.ViewListener
import com.meazza.tucarro.ui.auth.AuthListener
import com.meazza.tucarro.ui.auth.reset_password.DialogResetPassword
import com.meazza.tucarro.ui.auth.sign_up.SignUpActivity
import com.meazza.tucarro.ui.main.MainActivity
import com.meazza.tucarro.util.*
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.*
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity(), AuthListener,
    ViewListener {

    private val loginViewModel by inject<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil
            .setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
            .apply {
                lifecycleOwner = this@LoginActivity
                viewModel = loginViewModel
            }

        loginViewModel.authListener = this
        loginViewModel.viewListener = this

        setToolbar()
    }

    override fun onSuccess() {
        startActivity(intentFor<MainActivity>().newTask().clearTask())
    }

    override fun onFailure(messageCode: Int) {
        when (messageCode) {
            EMPTY_FIELDS -> showAlert(resources.getString(R.string.empty_fields))
            INVALID_EMAIL -> showAlert(resources.getString(R.string.invalid_email))
            INVALID_PASSWORD -> showAlert(resources.getString(R.string.invalid_password))
            WRONG_PASSWORD -> showAlert(resources.getString(R.string.wrong_password))
            USER_NOT_FOUND -> showAlert(resources.getString(R.string.user_not_found))
            CONFIRM_YOUR_EMAIL -> showAlert(resources.getString(R.string.confirm_email))
            LOGIN_ERROR -> showAlert(resources.getString(R.string.login_error))
            else -> showAlert(resources.getString(R.string.error))
        }
    }

    override fun gotoActivity() {
        startActivity<SignUpActivity>()
    }

    override fun openView() {
        val dialog = DialogResetPassword()
        dialog.show(supportFragmentManager, "Dialog Recover Password")
    }

    private fun setToolbar() {
        setSupportActionBar(tb_login)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showAlert(message: String) {
        alert(message) {
            okButton { it.dismiss() }
        }.show()
    }
}
