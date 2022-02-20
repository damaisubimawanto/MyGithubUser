package com.damai.mygithubuser.data.mapper

import com.damai.mygithubuser.core.BaseMapper
import com.damai.mygithubuser.data.model.UserSearchModel
import com.damai.mygithubuser.data.model.room_entity.UserSearchEntity

/**
 * Created by damai.subimawanto on 2/20/2022.
 */
class UserSearchModelListToUserSearchEntityListMapper : BaseMapper<List<UserSearchModel>, List<UserSearchEntity>>() {
    override fun map(value: List<UserSearchModel>): List<UserSearchEntity> {
        return value.map {
            UserSearchEntity(
                id = it.id,
                displayName = it.displayName,
                nickname = it.nickname,
                jobPosition = it.jobPosition,
                location = it.location,
                email = it.email,
                thumbnail = it.thumbnail,
                followers = it.followers,
                following = it.following,
                url = it.url
            )
        }
    }
}