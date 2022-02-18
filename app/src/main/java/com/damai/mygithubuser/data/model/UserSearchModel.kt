package com.damai.mygithubuser.data.model

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
data class UserSearchModel(
    val id: Int,
    val displayName: String?,
    val nickname: String?,
    val jobPosition: String?,
    val location: String?,
    val email: String?
)
