package com.damai.mygithubuser.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.damai.mygithubuser.data.model.*
import com.damai.mygithubuser.databinding.ItemLayoutDataUserRepoBinding
import com.damai.mygithubuser.databinding.ItemLayoutHeaderUserInfoBinding
import com.damai.mygithubuser.presentation.detail.viewholder.HeaderDetailViewHolder
import com.damai.mygithubuser.presentation.detail.viewholder.RepoDetailViewHolder
import com.damai.mygithubuser.presentation.detail.viewholder.UserDetailsViewHolder

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
class UserDetailsAdapter : ListAdapter<Any, UserDetailsViewHolder<Any>>(
    DIFF_UTIL
) {

    companion object {
        private const val HEADER_TYPE = 0
        private const val DATA_TYPE = 1

        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(
                oldItem: Any,
                newItem: Any
            ): Boolean {
                when (oldItem) {
                    is UserSearchModel -> {
                        when (newItem) {
                            is UserSearchModel -> {
                                return oldItem.id == newItem.id
                            }
                        }
                    }
                    is RepoDetailModel -> {
                        when (newItem) {
                            is RepoDetailModel -> {
                                return oldItem.id == newItem.id
                            }
                        }
                    }
                }
                return false
            }

            override fun areContentsTheSame(
                oldItem: Any,
                newItem: Any
            ): Boolean {
                when (oldItem) {
                    is UserSearchModel -> {
                        when (newItem) {
                            is UserSearchModel -> {
                                return oldItem.id == newItem.id
                            }
                        }
                    }
                    is RepoDetailModel -> {
                        when (newItem) {
                            is RepoDetailModel -> {
                                return oldItem.id == newItem.id
                            }
                        }
                    }
                }
                return false
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserDetailsViewHolder<Any> {
        return when (viewType) {
            HEADER_TYPE -> {
                val view = ItemLayoutHeaderUserInfoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderDetailViewHolder(binding = view) as UserDetailsViewHolder<Any>
            }
            else -> {
                val view = ItemLayoutDataUserRepoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                RepoDetailViewHolder(binding = view) as UserDetailsViewHolder<Any>
            }
        }
    }

    override fun onBindViewHolder(
        holder: UserDetailsViewHolder<Any>,
        position: Int
    ) {
        when (holder.itemViewType) {
            HEADER_TYPE -> {
                (holder as HeaderDetailViewHolder).bindData(
                    data = getItem(position) as UserSearchModel
                )
            }
            else -> {
                (holder as RepoDetailViewHolder).bindData(
                    data = getItem(position) as RepoDetailModel
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER_TYPE
            else -> DATA_TYPE
        }
    }
}