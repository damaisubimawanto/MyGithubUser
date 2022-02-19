package com.damai.mygithubuser.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
@Parcelize
data class UserSearchModel(
    val id: Int,
    var displayName: String?,
    var nickname: String?,
    var jobPosition: String?,
    var location: String?,
    var email: String?,
    var thumbnail: String?,
    var followers: Int,
    var following: Int,
    var url: String?,

    // For adapter
    var isDataFetched: Boolean? = null
): Parcelable