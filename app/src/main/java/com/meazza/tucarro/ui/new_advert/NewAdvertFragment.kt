package com.meazza.tucarro.ui.new_advert

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.meazza.tucarro.R
import com.meazza.tucarro.databinding.FragmentNewAdvertBinding
import com.meazza.tucarro.ui.ActivityListener
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.ext.android.inject

class NewAdvertFragment : Fragment(), ActivityListener {

    private val newAdvertViewModel by inject<NewAdvertViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        newAdvertViewModel.listener = this

        return DataBindingUtil.inflate<FragmentNewAdvertBinding>(
            inflater,
            R.layout.fragment_new_advert,
            container,
            false
        ).apply {
            lifecycleOwner = this@NewAdvertFragment
            viewModel = newAdvertViewModel
        }.root
    }

    private fun checkPermission() {
        Dexter.withActivity(activity)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    startActivity<NewAdvertActivity>()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                }

            }).check()
    }

    override fun gotoActivity() {
        checkPermission()
    }

    override fun openDialog() {
    }

    override fun addImage(requestCode: Int) {
    }
}
