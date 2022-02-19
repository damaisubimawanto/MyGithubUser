package com.damai.mygithubuser.presentation.detail.viewholder

import com.damai.mygithubuser.core.PicassoController
import com.damai.mygithubuser.data.model.RepoDetailModel
import com.damai.mygithubuser.databinding.ItemLayoutDataUserRepoBinding

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
class RepoDetailViewHolder(
    private val binding: ItemLayoutDataUserRepoBinding
) : UserDetailsViewHolder<RepoDetailModel>(binding = binding) {

    override fun bindData(data: RepoDetailModel) {
        binding.apply {
            tvRepoName.text = data.name
            tvRepoDescription.text = data.description
            tvRepoStarNumber.text = "${data.stars}"
            tvRepoUpdatedTime.text = data.lastUpdated

            PicassoController.loadImageCircle(
                url = data.thumbnail,
                imageView = ivUserThumbnail
            )
        }
    }
}