package com.damai.mygithubuser.domain

import com.damai.mygithubuser.core.DataSource
import com.damai.mygithubuser.core.FlowUseCase
import com.damai.mygithubuser.core.Resource
import com.damai.mygithubuser.data.model.UserSearchListModel
import com.damai.mygithubuser.data.repository.UserSearchRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
class GetUserListUseCase(
    private val repo: UserSearchRepository
) : FlowUseCase<DataSource, UserSearchListModel>() {

    override suspend fun execute(parameters: DataSource?): Flow<Resource<UserSearchListModel>> {
        return repo.getUserList(parameters!!)
    }
}