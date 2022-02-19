package com.damai.mygithubuser.module

import com.damai.mygithubuser.domain.GetUserListUseCase
import com.damai.mygithubuser.domain.GetUserRepoListUseCase
import com.damai.mygithubuser.domain.GetUserSearchInfoUseCase
import org.koin.dsl.module

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
val useCaseModule = module {
    single { GetUserListUseCase(get()) }
    single { GetUserSearchInfoUseCase(get()) }
    single { GetUserRepoListUseCase(get()) }
}