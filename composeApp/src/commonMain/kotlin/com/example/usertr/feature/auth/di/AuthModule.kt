package com.example.usertr.feature.auth.di

import com.example.usertr.feature.auth.presentation.splash.SplashViewModel
import com.example.usertr.feature.auth.presentation.userList.UserListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    viewModel { SplashViewModel(get(), get()) }
    viewModel { UserListViewModel(get(), get()) }
}