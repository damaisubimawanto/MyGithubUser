package com.damai.mygithubuser.data.model

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
open class UserDetailsModel {
    val id: Int = 0

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other == null -> false
            else -> {
                when (other) {
                    is UserDetailsModel -> {
                        id == other.id
                    }
                    else -> false
                }
            }
        }
    }

    override fun hashCode(): Int {
        return (id + 1).hashCode()
    }
}

data class UserInfoDetailModel(
    val data: UserSearchModel
): UserDetailsModel()

data class UserRepoDetailModel(
    val data: RepoDetailModel
): UserDetailsModel()