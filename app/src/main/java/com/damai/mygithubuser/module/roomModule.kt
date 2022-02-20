package com.damai.mygithubuser.module

import com.damai.mygithubuser.data.room.AppDatabase
import com.damai.mygithubuser.data.room.UserSearchListLocalSource
import com.damai.mygithubuser.data.room.UserSearchListLocalSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by damai.subimawanto on 2/20/2022.
 */
val roomModule = module {
    single { AppDatabase.buildDatabase(androidApplication()) }

    factory { get<AppDatabase>().userSearchDao() }
    factory<UserSearchListLocalSource> {
        UserSearchListLocalSourceImpl(
            userSearchDao = get(),
            userSearchToModelListMapper = get(),
            userSearchToEntityListMapper = get(),
            userSearchToModelMapper = get(),
            userSearchToEntityMapper = get()
        )
    }
}