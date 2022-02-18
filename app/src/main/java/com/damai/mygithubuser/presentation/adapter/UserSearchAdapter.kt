package com.damai.mygithubuser.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.damai.mygithubuser.data.model.UserSearchModel
import com.damai.mygithubuser.databinding.ItemLayoutUserSearchBinding

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
class UserSearchAdapter : ListAdapter<UserSearchModel, UserSearchAdapter.UserSearchViewHolder>(
    DIFF_UTIL
) {

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<UserSearchModel>() {
            override fun areItemsTheSame(
                oldItem: UserSearchModel,
                newItem: UserSearchModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UserSearchModel,
                newItem: UserSearchModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSearchViewHolder {
        val view = ItemLayoutUserSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserSearchViewHolder(binding = view)
    }

    override fun onBindViewHolder(holder: UserSearchViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class UserSearchViewHolder(
        private val binding: ItemLayoutUserSearchBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: UserSearchModel) {
            binding.apply {
                tvUserDisplayName.text = data.displayName
                tvUserNickname.text = data.nickname
                tvUserJobPosition.text = data.jobPosition
                tvUserLocation.text = data.location
                tvUserEmail.text = data.email
            }
        }
    }
}