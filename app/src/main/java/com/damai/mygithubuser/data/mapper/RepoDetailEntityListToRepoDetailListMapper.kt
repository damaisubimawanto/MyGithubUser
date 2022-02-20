package com.damai.mygithubuser.data.mapper

import com.damai.mygithubuser.core.BaseMapper
import com.damai.mygithubuser.data.model.RepoDetailModel
import com.damai.mygithubuser.data.model.room_entity.RepoDetailEntity

/**
 * Created by damai.subimawanto on 2/20/2022.
 */
class RepoDetailEntityListToRepoDetailListMapper : BaseMapper<List<RepoDetailEntity>, List<RepoDetailModel>>() {
    override fun map(value: List<RepoDetailEntity>): List<RepoDetailModel> {
        return value.map {
            RepoDetailModel(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnail = it.thumbnail,
                stars = it.stars,
                lastUpdated = it.lastUpdated
            )
        }
    }
}