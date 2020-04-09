package com.meazza.tucarro.ui.new_advert

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meazza.tucarro.ui.ActivityListener

class NewAdvertViewModel : ViewModel() {

    var etTitle = MutableLiveData<String>()
    var etPrice = MutableLiveData<String>()
    var etPhoneNumber = MutableLiveData<String>()
    var etDescription = MutableLiveData<String>()

    var listener: ActivityListener? = null

    fun fabAction() {
        listener?.gotoActivity()
    }

    fun addImage(requestCode: Int) {
        listener?.addImage(requestCode)
    }

    fun saveAdvert() {

    }
}
