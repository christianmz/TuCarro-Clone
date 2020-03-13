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
import org.koin.android.ext.android.inject


class DialogRecoverPass : DialogFragment(),
    AuthListener {

    private val dialogViewModel by inject<DialogRecoverPassViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialogViewModel.listener = this

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
    }

    override fun onFailure(message: String) {
    }
}
