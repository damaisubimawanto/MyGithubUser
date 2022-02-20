package com.damai.mygithubuser.data.repository

import com.damai.mygithubuser.core.DataSource
import com.damai.mygithubuser.core.NetworkResource
import com.damai.mygithubuser.core.Resource
import com.damai.mygithubuser.core.SchedulerProvider
import com.damai.mygithubuser.data.mapper.UserListInfoModelToUserSearchModelMapper
import com.damai.mygithubuser.data.mapper.UserSearchListToUserSearchModelMapper
import com.damai.mygithubuser.data.model.RequestUserInfoModel
import com.damai.mygithubuser.data.model.UserSearchListModel
import com.damai.mygithubuser.data.model.UserSearchModel
import com.damai.mygithubuser.data.room.UserSearchListLocalSource
import com.damai.mygithubuser.data.service.MainService
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
class UserSearchRepositoryImpl(
    private val mainService: MainService,
    private val userSearchListLocalSource: UserSearchListLocalSource,
    private val userSearchMapper: UserSearchListToUserSearchModelMapper,
    private val userSearchInfoMapper: UserListInfoModelToUserSearchModelMapper,
    private val schedulerProvider: SchedulerProvider
) : UserSearchRepository {

    override fun getUserList(dataSource: DataSource): Flow<Resource<UserSearchListModel>> {
        return object : NetworkResource<UserSearchListModel>(schedulerProvider) {
            override suspend fun remoteFetch(): UserSearchListModel {
                val request = mainService.getUserList()
                return userSearchMapper.map(request)
            }

            override suspend fun saveLocal(data: UserSearchListModel) {
                userSearchListLocalSource.saveUserSearchList(data.dataList)
            }

            override suspend fun localFetch(): UserSearchListModel {
                return UserSearchListModel(
                    dataList = userSearchListLocalSource.getUserSearchList()
                )
            }

            override fun shouldFetchFromRemote(): Boolean {
                return dataSource == DataSource.REMOTE
            }

            override fun shouldFetchRemoteAndSaveLocal(): Boolean {
                return true
            }

            override fun shouldFetchLocalOnError(): Boolean {
                return true
            }
        }.asFlow()
    }

    override fun getUserInfo(request: RequestUserInfoModel): Flow<Resource<UserSearchModel>> {
        return object : NetworkResource<UserSearchModel>(schedulerProvider) {
            override suspend fun remoteFetch(): UserSearchModel {
                val apiResponse = mainService.getUserInfo(username = request.username)
                return userSearchInfoMapper.map(apiResponse)
            }

            override suspend fun saveLocal(data: UserSearchModel) {
                userSearchListLocalSource.saveUserSearch(data = data)
            }

            override suspend fun localFetch(): UserSearchModel {
                return userSearchListLocalSource.getUserSearch(id = request.id)
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