package com.damai.mygithubuser.presentation.main.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.damai.mygithubuser.core.PicassoController
import com.damai.mygithubuser.data.model.UserSearchModel
import com.damai.mygithubuser.databinding.ItemLayoutUserSearchBinding

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
            binding.data = data
            binding.isSeperatorGone = isSeperatorGone
            binding.executePendingBindings()

            PicassoController.loadImageCircle(
                url = data.thumbnail,
                imageView = binding.ivUserThumbnail
            )

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

            itemView.setOnClickListener {
                mCallback.onItemClicked(data = data)
            }
        }
    }

    interface Callback {
        fun needFetchData(id: Int, username: String)
        fun onItemClicked(data: UserSearchModel)
    }
}