package com.damai.mygithubuser.data.model.room_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by damai.subimawanto on 2/20/2022.
 */
@Entity(tableName = "user_search_entity")
data class UserSearchEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "display_name") var displayName: String?,
    @ColumnInfo(name = "job_position") var jobPosition: String?,
    var nickname: String?,
    var location: String?,
    var email: String?,
    var thumbnail: String?,
    var followers: Int,
    var following: Int,
    var url: String?,
)
