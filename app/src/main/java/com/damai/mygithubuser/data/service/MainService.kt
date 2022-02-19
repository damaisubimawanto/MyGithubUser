package com.damai.mygithubuser.data.service

import com.damai.mygithubuser.data.model.RepoListModel
import com.damai.mygithubuser.data.model.UserListModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
interface MainService {
    @GET("/users")
    suspend fun getUserList(): List<UserListModel>

    @GET("/users/{username}")
    suspend fun getUserInfo(
        @Path("username") username: String
    ): UserListModel

    @GET("/users/{username}/repos")
    suspend fun getUserRepoList(
        @Path("username") username: String
    ): List<RepoListModel>
}