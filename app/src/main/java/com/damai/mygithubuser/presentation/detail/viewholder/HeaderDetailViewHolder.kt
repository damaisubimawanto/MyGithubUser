package com.damai.mygithubuser.presentation.detail.viewholder

import com.damai.mygithubuser.R
import com.damai.mygithubuser.core.PicassoController
import com.damai.mygithubuser.data.model.UserSearchModel
import com.damai.mygithubuser.data.util.DisplayHelper
import com.damai.mygithubuser.databinding.ItemLayoutHeaderUserInfoBinding

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
class HeaderDetailViewHolder(
    private val binding: ItemLayoutHeaderUserInfoBinding
) : UserDetailsViewHolder<UserSearchModel>(binding = binding) {

    override fun bindData(data: UserSearchModel) {
        val followersText = DisplayHelper(itemView.context).generateFollowers(
            followersNumber = data.followers
        )
        val followingText = DisplayHelper(itemView.context).generateFollowing(
            followingNumber = data.following
        )

        binding.apply {
            tvUserDisplayName.text = data.displayName
            tvUserNickname.text = data.nickname
            tvUserJobPosition.text = data.jobPosition
            tvFollowersNumber.text = followersText
            tvFollowingNumber.text = followingText
            tvUserLocation.text = data.location
            tvUserEmail.text = data.email

            PicassoController.loadImageCircle(
                url = data.thumbnail,
                imageView = ivUserThumbnail
            )
        }
    }
}