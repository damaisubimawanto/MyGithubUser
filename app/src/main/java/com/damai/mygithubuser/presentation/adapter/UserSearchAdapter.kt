package com.damai.mygithubuser.presentation.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.damai.mygithubuser.core.customview.DefaultCircleTransform
import com.damai.mygithubuser.data.model.UserSearchModel
import com.damai.mygithubuser.databinding.ItemLayoutUserSearchBinding
import com.squareup.picasso.Picasso

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
class UserSearchAdapter(
    private val mCallback: Callback
) : ListAdapter<UserSearchModel, UserSearchAdapter.UserSearchViewHolder>(
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
        holder.bindData(
            data = getItem(position),
            isSeperatorGone = position == itemCount
        )
    }

    inner class UserSearchViewHolder(
        private val binding: ItemLayoutUserSearchBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: UserSearchModel, isSeperatorGone: Boolean) {
            binding.apply {
                tvUserDisplayName.text = data.displayName
                tvUserNickname.text = data.nickname
                tvUserJobPosition.text = data.jobPosition
                tvUserLocation.text = data.location
                tvUserEmail.text = data.email
                seperator.visibility = if (isSeperatorGone) {
                    View.GONE
                } else {
                    View.VISIBLE
                }

                Picasso.get().load(data.thumbnail).fit().centerCrop()
                    .transform(DefaultCircleTransform())
                    .into(ivUserThumbnail)
            }

            when (data.isDataFetched) {
                null -> {
                    val uri = try {
                        Uri.parse(data.url)
                    } catch (e: NullPointerException) {
                        null
                    }
                    uri?.lastPathSegment?.let { username ->
                        mCallback.needFetchData(
                            id = data.id,
                            username = username
                        )
                        data.isDataFetched = false
                    }
                }
                else -> {}
            }
        }
    }

    interface Callback {
        fun needFetchData(id: Int, username: String)
    }
}