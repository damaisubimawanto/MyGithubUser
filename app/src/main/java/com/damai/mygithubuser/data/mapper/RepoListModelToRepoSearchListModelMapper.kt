package com.damai.mygithubuser.data.mapper

import com.damai.mygithubuser.core.BaseMapper
import com.damai.mygithubuser.data.model.RepoDetailModel
import com.damai.mygithubuser.data.model.RepoListModel
import com.damai.mygithubuser.data.model.RepoSearchListModel

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
class RepoListModelToRepoSearchListModelMapper : BaseMapper<List<RepoListModel>, RepoSearchListModel>() {
    override fun map(value: List<RepoListModel>): RepoSearchListModel {
        return RepoSearchListModel(
            dataList = value.map {
                RepoDetailModel(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    userId = it.owner?.id ?: 0,
                    thumbnail = it.owner?.avatarUrl,
                    stars = it.stargazersCount,
                    lastUpdated = it.updatedAt
                )
            }
        )
    }
}