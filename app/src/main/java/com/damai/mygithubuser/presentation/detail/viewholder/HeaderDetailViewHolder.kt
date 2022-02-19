package com.damai.mygithubuser.presentation.detail.viewholder

import com.damai.mygithubuser.R
import com.damai.mygithubuser.core.PicassoController
import com.damai.mygithubuser.data.model.UserSearchModel
import com.damai.mygithubuser.databinding.ItemLayoutHeaderUserInfoBinding

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
class HeaderDetailViewHolder(
    private val binding: ItemLayoutHeaderUserInfoBinding
) : UserDetailsViewHolder<UserSearchModel>(binding = binding) {

    override fun bindData(data: UserSearchModel) {
        binding.apply {
            tvUserDisplayName.text = data.displayName
            tvUserNickname.text = data.nickname
            tvUserJobPosition.text = data.jobPosition
            tvFollowersNumber.text = itemView.context.getString(R.string.text_user_followers_number, data.followers)
            tvFollowingNumber.text = itemView.context.getString(R.string.text_user_following_number, data.following)
            tvUserLocation.text = data.location
            tvUserEmail.text = data.email

            PicassoController.loadImageCircle(
                url = data.thumbnail,
                imageView = ivUserThumbnail
            )
        }
    }
}