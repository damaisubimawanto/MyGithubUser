package com.damai.mygithubuser.data.repository

import com.damai.mygithubuser.core.Resource
import com.damai.mygithubuser.data.model.UserSearchListModel
import com.damai.mygithubuser.data.model.UserSearchModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
interface UserSearchRepository {

    @Throws(Exception::class)
    fun getUserList(): Flow<Resource<UserSearchListModel>>

    @Throws(Exception::class)
    fun getUserInfo(username: String): Flow<Resource<UserSearchModel>>
}