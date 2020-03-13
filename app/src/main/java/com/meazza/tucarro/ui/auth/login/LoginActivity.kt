package com.meazza.tucarro.ui.auth.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.meazza.tucarro.R
import com.meazza.tucarro.databinding.ActivityLoginBinding
import com.meazza.tucarro.ui.ViewListener
import com.meazza.tucarro.ui.auth.AuthListener
import com.meazza.tucarro.ui.auth.recover_pass.DialogRecoverPass
import com.meazza.tucarro.ui.auth.sign_up.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
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

    private fun setToolbar() {
        setSupportActionBar(tb_login)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSuccess() {
    }

    override fun onFailure(message: String) {

    }

    override fun gotoActivity() {
        startActivity<SignUpActivity>()
    }

    override fun openView() {
        val dialog = DialogRecoverPass()
        dialog.show(supportFragmentManager, "CustomDialogFragment")
    }
}
