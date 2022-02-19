package com.damai.mygithubuser.data.mapper

import com.damai.mygithubuser.core.BaseMapper
import com.damai.mygithubuser.data.model.UserListModel
import com.damai.mygithubuser.data.model.UserSearchListModel
import com.damai.mygithubuser.data.model.UserSearchModel

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
class UserSearchListToUserSearchModelMapper : BaseMapper<List<UserListModel>, UserSearchListModel>() {
    override fun map(value: List<UserListModel>): UserSearchListModel {
        return UserSearchListModel(
            dataList = value.map {
                UserSearchModel(
                    id = it.id,
                    displayName = it.name,
                    nickname = it.twitterUsername,
                    jobPosition = it.company,
                    location = it.location,
                    email = it.email,
                    thumbnail = it.avatarUrl,
                    followers = it.followers,
                    following = it.following,
                    url = it.url
                )
            }
        )
    }
}