package com.damai.mygithubuser.data.room

import androidx.room.*
import com.damai.mygithubuser.data.model.room_entity.RepoDetailEntity
import com.damai.mygithubuser.data.model.room_entity.UserSearchEntity

/**
 * Created by damai.subimawanto on 2/20/2022.
 */
@Dao
interface UserSearchDao {
    @Query("SELECT * FROM user_search_entity")
    fun getUserSearchList(): List<UserSearchEntity>

    @Query("SELECT * FROM repo_detail_entity")
    fun getRepoDetailList(): List<RepoDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUserSearch(userSearchList: List<UserSearchEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRepoDetail(repoDetailList: List<RepoDetailEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUserSearchDetail(data: UserSearchEntity)
}