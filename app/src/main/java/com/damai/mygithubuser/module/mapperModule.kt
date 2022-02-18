package com.damai.mygithubuser.module

import com.damai.mygithubuser.data.mapper.UserSearchListToUserSearchModelMapper
import org.koin.dsl.module

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
val mapperModule = module {
    factory { UserSearchListToUserSearchModelMapper() }
}