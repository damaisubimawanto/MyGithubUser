package com.damai.mygithubuser.module

import com.damai.mygithubuser.data.mapper.*
import org.koin.dsl.module

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
val mapperModule = module {
    factory { UserSearchListToUserSearchModelMapper() }
    factory { UserListInfoModelToUserSearchModelMapper(
        displayHelper = get()
    ) }
    factory { RepoListModelToRepoSearchListModelMapper() }
    factory { UserSearchEntityListToUserSearchModelListMapper() }
    factory { UserSearchModelListToUserSearchEntityListMapper() }
    factory { UserSearchEntityToUserSearchModelMapper() }
    factory { UserSearchModelToUserSearchEntityMapper() }

    factory { RepoDetailEntityListToRepoDetailListMapper() }
    factory { RepoDetailListToRepoDetailEntityListMapper() }
}