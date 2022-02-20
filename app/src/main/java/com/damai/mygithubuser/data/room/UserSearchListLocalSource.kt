package com.damai.mygithubuser.data.room

import com.damai.mygithubuser.data.model.UserSearchModel

/**
 * Created by damai.subimawanto on 2/20/2022.
 */
interface UserSearchListLocalSource {
    suspend fun getUserSearchList(): List<UserSearchModel>
    suspend fun saveUserSearchList(list: List<UserSearchModel>)
    suspend fun saveUserSearch(data: UserSearchModel)
}