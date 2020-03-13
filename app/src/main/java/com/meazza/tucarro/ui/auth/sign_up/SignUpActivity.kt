package com.meazza.tucarro.ui.auth.sign_up

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.meazza.tucarro.R
import com.meazza.tucarro.databinding.ActivitySignUpBinding
import com.meazza.tucarro.ui.auth.AuthListener
import kotlinx.android.synthetic.main.activity_sign_up.*
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

        signUpViewModel.listener = this

        setToolbar()
    }

    private fun setToolbar() {
        setSupportActionBar(tb_sign_up)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSuccess() {
    }

    override fun onFailure(message: String) {
    }
}
