package com.damai.mygithubuser.data.repository

import com.damai.mygithubuser.core.Resource
import com.damai.mygithubuser.data.model.RepoSearchListModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
interface UserRepoSearchRepository {

    @Throws(Exception::class)
    fun getUserRepoList(username: String): Flow<Resource<RepoSearchListModel>>
}