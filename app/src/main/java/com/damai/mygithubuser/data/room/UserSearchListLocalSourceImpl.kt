package com.damai.mygithubuser.data.room

import com.damai.mygithubuser.data.mapper.UserSearchEntityListToUserSearchModelListMapper
import com.damai.mygithubuser.data.mapper.UserSearchEntityToUserSearchModelMapper
import com.damai.mygithubuser.data.mapper.UserSearchModelListToUserSearchEntityListMapper
import com.damai.mygithubuser.data.mapper.UserSearchModelToUserSearchEntityMapper
import com.damai.mygithubuser.data.model.UserSearchModel

/**
 * Created by damai.subimawanto on 2/20/2022.
 */
class UserSearchListLocalSourceImpl(
    private val userSearchDao: UserSearchDao,
    private val userSearchToModelListMapper: UserSearchEntityListToUserSearchModelListMapper,
    private val userSearchToEntityListMapper: UserSearchModelListToUserSearchEntityListMapper,
    private val userSearchToModelMapper: UserSearchEntityToUserSearchModelMapper,
    private val userSearchToEntityMapper: UserSearchModelToUserSearchEntityMapper
) : UserSearchListLocalSource {

    override suspend fun getUserSearchList(): List<UserSearchModel> {
        return userSearchToModelListMapper.map(userSearchDao.getUserSearchList())
    }

    override suspend fun getUserSearch(id: Int): UserSearchModel {
        return userSearchToModelMapper.map(userSearchDao.getUserSearch(userId = id))
    }

    override suspend fun saveUserSearchList(list: List<UserSearchModel>) {
        userSearchDao.insertAllUserSearch(userSearchToEntityListMapper.map(list))
    }

    override suspend fun saveUserSearch(data: UserSearchModel) {
        userSearchDao.updateUserSearchDetail(userSearchToEntityMapper.map(data))
    }
}