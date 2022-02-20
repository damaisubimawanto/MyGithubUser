package com.damai.mygithubuser.data.mapper

import com.damai.mygithubuser.core.BaseMapper
import com.damai.mygithubuser.data.model.UserSearchModel
import com.damai.mygithubuser.data.model.room_entity.UserSearchEntity

/**
 * Created by damai.subimawanto on 2/20/2022.
 */
class UserSearchModelToUserSearchEntityMapper : BaseMapper<UserSearchModel, UserSearchEntity>() {
    override fun map(value: UserSearchModel): UserSearchEntity {
        return UserSearchEntity(
            id = value.id,
            displayName = value.displayName,
            nickname = value.nickname,
            jobPosition = value.jobPosition,
            location = value.location,
            email = value.email,
            thumbnail = value.thumbnail,
            followers = value.followers,
            following = value.following,
            url = value.url
        )
    }
}