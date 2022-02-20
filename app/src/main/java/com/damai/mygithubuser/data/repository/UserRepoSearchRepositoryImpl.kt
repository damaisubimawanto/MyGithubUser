package com.damai.mygithubuser.data.repository

import com.damai.mygithubuser.core.NetworkResource
import com.damai.mygithubuser.core.Resource
import com.damai.mygithubuser.core.SchedulerProvider
import com.damai.mygithubuser.data.mapper.RepoListModelToRepoSearchListModelMapper
import com.damai.mygithubuser.data.model.RepoSearchListModel
import com.damai.mygithubuser.data.model.RequestUserInfoModel
import com.damai.mygithubuser.data.room.UserRepoListLocalSource
import com.damai.mygithubuser.data.service.MainService
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
class UserRepoSearchRepositoryImpl(
    private val mainService: MainService,
    private val userRepoListLocalSource: UserRepoListLocalSource,
    private val repoListMapper: RepoListModelToRepoSearchListModelMapper,
    private val schedulerProvider: SchedulerProvider
) : UserRepoSearchRepository {

    override fun getUserRepoList(requestModel: RequestUserInfoModel): Flow<Resource<RepoSearchListModel>> {
        return object : NetworkResource<RepoSearchListModel>(schedulerProvider) {
            override suspend fun remoteFetch(): RepoSearchListModel {
                val request = mainService.getUserRepoList(username = requestModel.username)
                return repoListMapper.map(request)
            }

            override suspend fun saveLocal(data: RepoSearchListModel) {
                userRepoListLocalSource.saveUserRepoList(data.dataList)
            }

            override suspend fun localFetch(): RepoSearchListModel {
                return RepoSearchListModel(
                    dataList = userRepoListLocalSource.getUserRepoList(userId = requestModel.id)
                )
            }

            override fun shouldFetchRemoteAndSaveLocal(): Boolean {
                return true
            }

            override fun shouldFetchLocalOnError(): Boolean {
                return true
            }
        }.asFlow()
    }
}