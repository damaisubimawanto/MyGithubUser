package com.damai.mygithubuser.module

import com.damai.mygithubuser.data.repository.UserRepoSearchRepository
import com.damai.mygithubuser.data.repository.UserRepoSearchRepositoryImpl
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
            userSearchInfoMapper = get(),
            schedulerProvider = get()
        )
    }

    single<UserRepoSearchRepository> {
        UserRepoSearchRepositoryImpl(
            mainService = get(),
            repoListMapper = get(),
            schedulerProvider = get()
        )
    }
}