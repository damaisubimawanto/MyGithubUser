package com.damai.mygithubuser.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by damai.subimawanto on 2/19/2022.
 *
 * This model is for storing the response data from API.
 */
class RepoListModel {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("stargazers_count")
    var stargazersCount: Int = 0

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("owner")
    var owner: RepoOwnerModel? = null

    class RepoOwnerModel {
        @SerializedName("id")
        var id: Int = 0

        @SerializedName("avatar_url")
        var avatarUrl: String? = null
    }
}