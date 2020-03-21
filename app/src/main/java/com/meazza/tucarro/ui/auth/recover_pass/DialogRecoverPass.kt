package com.meazza.tucarro.ui.auth.recover_pass

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.meazza.tucarro.R
import com.meazza.tucarro.databinding.DialogRecoverPasswordBinding
import com.meazza.tucarro.ui.auth.AuthListener
import com.meazza.tucarro.util.INVALID_EMAIL
import com.meazza.tucarro.util.USER_NOT_FOUND
import org.jetbrains.anko.support.v4.longToast
import org.koin.android.ext.android.inject


class DialogRecoverPass : DialogFragment(), AuthListener {

    private val dialogViewModel by inject<DialogRecoverPassViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialogViewModel.authListener = this

        return inflater.inflate(R.layout.dialog_recover_password, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBinding = DataBindingUtil
            .inflate<DialogRecoverPasswordBinding>(
                LayoutInflater.from(context),
                R.layout.dialog_recover_password,
                null,
                false
            ).apply {
                lifecycleOwner = lifecycleOwner
                viewModel = dialogViewModel
            }

        val dialog = AlertDialog.Builder(context).apply {
            setView(dialogBinding.root)
        }

        return dialog.create()
    }

    override fun onSuccess() {
        longToast(R.string.email_reset_password)
        dismiss()
    }

    override fun onFailure(messageCode: Int) {
        when (messageCode) {
            INVALID_EMAIL -> longToast(R.string.invalid_email)
            USER_NOT_FOUND -> longToast(R.string.user_not_found)
            else -> longToast(R.string.error)
        }
    }
}
