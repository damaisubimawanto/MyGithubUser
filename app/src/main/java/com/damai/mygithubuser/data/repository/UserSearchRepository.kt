package com.damai.mygithubuser.data.repository

import com.damai.mygithubuser.core.Resource
import com.damai.mygithubuser.data.model.UserSearchListModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
interface UserSearchRepository {

    @Throws(Exception::class)
    fun getUserList(): Flow<Resource<UserSearchListModel>>
}