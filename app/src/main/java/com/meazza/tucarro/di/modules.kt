package com.meazza.tucarro.di

import com.meazza.tucarro.network.AuthService
import com.meazza.tucarro.network.DatabaseConnection
import com.meazza.tucarro.repository.AuthRepository
import com.meazza.tucarro.repository.MainRepository
import com.meazza.tucarro.ui.auth.login.LoginViewModel
import com.meazza.tucarro.ui.auth.reset_password.ResetPasswordViewModel
import com.meazza.tucarro.ui.auth.sign_up.SignUpViewModel
import com.meazza.tucarro.ui.main.MainViewModel
import com.meazza.tucarro.ui.new_advert.NewAdvertViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single { AuthService }
    single { DatabaseConnection }
    single { AuthRepository(get(), get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { ResetPasswordViewModel(get()) }
}

val mainModule = module {
    single { MainRepository(get()) }
    viewModel { MainViewModel(get()) }
}

val newAdvertModule = module {
    viewModel { NewAdvertViewModel() }
}
