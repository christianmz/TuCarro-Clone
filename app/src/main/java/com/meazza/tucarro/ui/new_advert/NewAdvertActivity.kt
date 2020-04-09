package com.meazza.tucarro.ui.new_advert

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.meazza.tucarro.R
import com.meazza.tucarro.databinding.ActivityNewAdvertBinding
import com.meazza.tucarro.ui.ActivityListener
import kotlinx.android.synthetic.main.activity_new_advert.*
import org.koin.android.ext.android.inject

class NewAdvertActivity : AppCompatActivity(), ActivityListener {

    private val imageList = ArrayList<String>()

    private val newAdvertViewModel by inject<NewAdvertViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityNewAdvertBinding>(
            this, R.layout.activity_new_advert
        ).apply {
            lifecycleOwner = this@NewAdvertActivity
            viewModel = newAdvertViewModel
        }

        newAdvertViewModel.listener = this

        setToolbar()
    }

    private fun setToolbar() {
        setSupportActionBar(tb_new_advert)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            val image = data?.data
            val path = image.toString()

            when (requestCode) {
                101 -> iv_new_advert1.setImageURI(image)
                102 -> iv_new_advert2.setImageURI(image)
                103 -> iv_new_advert3.setImageURI(image)
            }
            imageList.add(path)
        }
    }

    override fun gotoActivity() {
    }

    override fun addImage(requestCode: Int) {
        startActivityForResult(
            Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            ), requestCode
        )
    }

    override fun openDialog() {
    }
}
