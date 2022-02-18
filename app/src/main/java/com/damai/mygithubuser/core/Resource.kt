package com.damai.mygithubuser.core

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
sealed class Resource<out T> {
    data class Success<T>(val model: T? = null, val source: DataSource) : Resource<T>()
    data class Error(val errorData: ErrorData) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    object None : Resource<Nothing>()
}
