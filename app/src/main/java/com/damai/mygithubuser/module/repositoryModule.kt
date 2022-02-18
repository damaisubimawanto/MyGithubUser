package com.damai.mygithubuser.module

import com.damai.mygithubuser.data.repository.UserSearchRepository
import com.damai.mygithubuser.data.repository.UserSearchRepositoryImpl
import org.koin.dsl.module

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
val repositoryModule = module {
    single<UserSearchRepository> {
        UserSearchRepositoryImpl(
            mainService = get(),
            userSearchMapper = get(),
            schedulerProvider = get()
        )
    }
}