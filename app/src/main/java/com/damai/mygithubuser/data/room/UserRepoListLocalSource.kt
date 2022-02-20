package com.damai.mygithubuser.data.room

import com.damai.mygithubuser.data.model.RepoDetailModel

/**
 * Created by damai.subimawanto on 2/20/2022.
 */
interface UserRepoListLocalSource {
    suspend fun getUserRepoList(userId: Int): List<RepoDetailModel>
    suspend fun saveUserRepoList(list: List<RepoDetailModel>)
}