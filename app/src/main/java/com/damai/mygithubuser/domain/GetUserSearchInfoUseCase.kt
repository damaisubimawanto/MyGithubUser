package com.damai.mygithubuser.domain

import com.damai.mygithubuser.core.FlowUseCase
import com.damai.mygithubuser.core.Resource
import com.damai.mygithubuser.data.model.RequestUserInfoModel
import com.damai.mygithubuser.data.model.UserSearchModel
import com.damai.mygithubuser.data.repository.UserSearchRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
class GetUserSearchInfoUseCase(
    private val repo: UserSearchRepository
) : FlowUseCase<RequestUserInfoModel, UserSearchModel>() {

    override suspend fun execute(parameters: RequestUserInfoModel?): Flow<Resource<UserSearchModel>> {
        return repo.getUserInfo(parameters!!)
    }
}