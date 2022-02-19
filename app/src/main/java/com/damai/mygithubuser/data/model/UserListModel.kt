package com.damai.mygithubuser.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by damai.subimawanto on 2/18/2022.
 *
 * This model is for storing the response data from API.
 */
class UserListModel {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("location")
    var location: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("twitter_username")
    var twitterUsername: String? = null

    @SerializedName("company")
    var company: String? = null

    @SerializedName("followers")
    var followers: Int = 0

    @SerializedName("following")
    var following: Int = 0

    @SerializedName("url")
    var url: String? = null
}