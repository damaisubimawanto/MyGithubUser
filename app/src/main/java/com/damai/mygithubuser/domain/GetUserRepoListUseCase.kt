package com.damai.mygithubuser.domain

import com.damai.mygithubuser.core.FlowUseCase
import com.damai.mygithubuser.core.Resource
import com.damai.mygithubuser.data.model.RepoSearchListModel
import com.damai.mygithubuser.data.repository.UserRepoSearchRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
class GetUserRepoListUseCase(
    private val repo: UserRepoSearchRepository
) : FlowUseCase<String, RepoSearchListModel>() {

    override suspend fun execute(parameters: String?): Flow<Resource<RepoSearchListModel>> {
        return repo.getUserRepoList(parameters!!)
    }
}