package com.damai.mygithubuser.data.repository

import com.damai.mygithubuser.core.NetworkResource
import com.damai.mygithubuser.core.Resource
import com.damai.mygithubuser.core.SchedulerProvider
import com.damai.mygithubuser.data.mapper.RepoListModelToRepoSearchListModelMapper
import com.damai.mygithubuser.data.model.RepoSearchListModel
import com.damai.mygithubuser.data.service.MainService
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
class UserRepoSearchRepositoryImpl(
    private val mainService: MainService,
    private val repoListMapper: RepoListModelToRepoSearchListModelMapper,
    private val schedulerProvider: SchedulerProvider
) : UserRepoSearchRepository {

    override fun getUserRepoList(username: String): Flow<Resource<RepoSearchListModel>> {
        return object : NetworkResource<RepoSearchListModel>(schedulerProvider) {
            override suspend fun remoteFetch(): RepoSearchListModel {
                val request = mainService.getUserRepoList(username = username)
                return repoListMapper.map(request)
            }
        }.asFlow()
    }
}