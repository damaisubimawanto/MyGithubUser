package com.damai.mygithubuser.data.model.room_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by damai.subimawanto on 2/20/2022.
 */
@Entity(tableName = "repo_detail_entity")
data class RepoDetailEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "last_updated") val lastUpdated: String?,
    val name: String?,
    val description: String?,
    val userId: Int,
    val thumbnail: String?,
    val stars: Int
)