package com.damai.mygithubuser.presentation.detail.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
abstract class UserDetailsViewHolder<T>(
    binding: ViewDataBinding
): RecyclerView.ViewHolder(binding.root) {
    abstract fun bindData(data: T)
}