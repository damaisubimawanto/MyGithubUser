package com.damai.mygithubuser.module

import com.damai.mygithubuser.presentation.detail.UserDetailViewModel
import com.damai.mygithubuser.presentation.main.MainPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
val viewModelModule = module {
    viewModel { MainPageViewModel(get(), get()) }
    viewModel { UserDetailViewModel(get()) }
}