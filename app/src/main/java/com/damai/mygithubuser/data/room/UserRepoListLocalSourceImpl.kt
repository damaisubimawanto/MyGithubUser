package com.damai.mygithubuser.data.room

import com.damai.mygithubuser.data.mapper.RepoDetailEntityListToRepoDetailListMapper
import com.damai.mygithubuser.data.mapper.RepoDetailListToRepoDetailEntityListMapper
import com.damai.mygithubuser.data.model.RepoDetailModel

/**
 * Created by damai.subimawanto on 2/20/2022.
 */
class UserRepoListLocalSourceImpl(
    private val userSearchDao: UserSearchDao,
    private val repoDetailToModelMapper: RepoDetailEntityListToRepoDetailListMapper,
    private val repoDetailToEntityMapper: RepoDetailListToRepoDetailEntityListMapper
) : UserRepoListLocalSource {

    override suspend fun getUserRepoList(): List<RepoDetailModel> {
        return repoDetailToModelMapper.map(userSearchDao.getRepoDetailList())
    }

    override suspend fun saveUserRepoList(list: List<RepoDetailModel>) {
        userSearchDao.insertAllRepoDetail(repoDetailToEntityMapper.map(list))
    }
}