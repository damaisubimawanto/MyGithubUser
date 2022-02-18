package com.damai.mygithubuser.data.repository

import com.damai.mygithubuser.core.NetworkResource
import com.damai.mygithubuser.core.Resource
import com.damai.mygithubuser.core.SchedulerProvider
import com.damai.mygithubuser.data.mapper.UserSearchListToUserSearchModelMapper
import com.damai.mygithubuser.data.model.UserSearchListModel
import com.damai.mygithubuser.data.service.MainService
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
class UserSearchRepositoryImpl(
    private val mainService: MainService,
    private val userSearchMapper: UserSearchListToUserSearchModelMapper,
    private val schedulerProvider: SchedulerProvider
) : UserSearchRepository {

    override fun getUserList(): Flow<Resource<UserSearchListModel>> {
        return object : NetworkResource<UserSearchListModel>(schedulerProvider) {
            override suspend fun remoteFetch(): UserSearchListModel {
                val request = mainService.getUserList()
                return userSearchMapper.map(request)
            }
        }.asFlow()
    }
}