package com.meazza.tucarro.di

import com.meazza.tucarro.network.AuthService
import com.meazza.tucarro.repository.AuthRepository
import com.meazza.tucarro.repository.MainRepository
import com.meazza.tucarro.ui.auth.login.LoginViewModel
import com.meazza.tucarro.ui.auth.recover_pass.DialogRecoverPassViewModel
import com.meazza.tucarro.ui.auth.sign_up.SignUpViewModel
import com.meazza.tucarro.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single { AuthService }
    single { AuthRepository(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { DialogRecoverPassViewModel(get()) }
}

val mainModule = module {
    single { MainRepository() }
    viewModel { MainViewModel(get()) }
}
