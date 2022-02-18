package com.damai.mygithubuser.data.mapper

import com.damai.mygithubuser.core.BaseMapper
import com.damai.mygithubuser.data.model.UserListModel
import com.damai.mygithubuser.data.model.UserSearchListModel

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
class UserSearchListToUserSearchModelMapper : BaseMapper<UserListModel, UserSearchListModel>() {
    override fun map(value: UserListModel): UserSearchListModel {
        TODO("Not yet implemented")
    }
}