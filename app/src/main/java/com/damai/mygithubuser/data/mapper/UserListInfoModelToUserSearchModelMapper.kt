package com.damai.mygithubuser.data.mapper

import com.damai.mygithubuser.core.BaseMapper
import com.damai.mygithubuser.data.model.UserListModel
import com.damai.mygithubuser.data.model.UserSearchModel

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
class UserListInfoModelToUserSearchModelMapper : BaseMapper<UserListModel, UserSearchModel>() {
    override fun map(value: UserListModel): UserSearchModel {
        return UserSearchModel(
            id = value.id,
            displayName = value.name,
            nickname = value.twitterUsername,
            jobPosition = value.company,
            location = value.location,
            email = value.email,
            thumbnail = value.avatarUrl,
            url = value.url
        )
    }
}