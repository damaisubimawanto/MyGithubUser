package com.damai.mygithubuser.data.model

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
data class UserSearchModel(
    val id: Int,
    var displayName: String?,
    var nickname: String?,
    var jobPosition: String?,
    var location: String?,
    var email: String?,
    var thumbnail: String?,
    var url: String?,

    // For adapter
    var isDataFetched: Boolean? = null
)
