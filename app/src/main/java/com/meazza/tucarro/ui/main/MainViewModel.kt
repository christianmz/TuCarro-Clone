package com.meazza.tucarro.ui.main

import androidx.lifecycle.ViewModel
import com.meazza.tucarro.repository.MainRepository

class MainViewModel(repository: MainRepository) : ViewModel() {

    val user = repository.user
    val signOut = repository.signOut
}