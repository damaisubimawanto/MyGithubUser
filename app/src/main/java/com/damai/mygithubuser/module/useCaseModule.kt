package com.damai.mygithubuser.module

import com.damai.mygithubuser.domain.GetUserListUseCase
import org.koin.dsl.module

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
val useCaseModule = module {
    single { GetUserListUseCase(get()) }
}