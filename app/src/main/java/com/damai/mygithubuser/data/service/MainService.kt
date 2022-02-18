package com.damai.mygithubuser.data.service

import com.damai.mygithubuser.data.model.UserListModel
import retrofit2.http.GET

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
interface MainService {
    @GET("/users")
    suspend fun getUserList(): UserListModel
}