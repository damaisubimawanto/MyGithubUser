package com.damai.mygithubuser.data.model

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
data class RepoDetailModel(
    val id: Int,
    val name: String?,
    val description: String?,
    val thumbnail: String?,
    val stars: Int,
    val lastUpdated: String?
)
