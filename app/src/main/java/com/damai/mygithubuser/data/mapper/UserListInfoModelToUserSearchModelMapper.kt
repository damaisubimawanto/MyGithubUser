package com.damai.mygithubuser.data.mapper

import com.damai.mygithubuser.core.BaseMapper
import com.damai.mygithubuser.data.model.UserListModel
import com.damai.mygithubuser.data.model.UserSearchModel
import com.damai.mygithubuser.data.util.DisplayHelper

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
class UserListInfoModelToUserSearchModelMapper(
    private val displayHelper: DisplayHelper
) : BaseMapper<UserListModel, UserSearchModel>() {
    override fun map(value: UserListModel): UserSearchModel {
        return UserSearchModel(
            id = value.id,
            displayName = displayHelper.generateDisplayName(value.name),
            nickname = displayHelper.generateNickname(value.twitterUsername),
            jobPosition = displayHelper.generateJobPosition(value.company),
            location = displayHelper.generateLocation(value.location),
            email = displayHelper.generateEmail(value.email),
            followers = value.followers,
            following = value.following,
            thumbnail = value.avatarUrl,
            url = value.url
        )
    }
}