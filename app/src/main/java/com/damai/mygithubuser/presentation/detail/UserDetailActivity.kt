package com.damai.mygithubuser.presentation.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.damai.mygithubuser.R
import com.damai.mygithubuser.core.BaseActivity
import com.damai.mygithubuser.core.ViewDataBindingOwner
import com.damai.mygithubuser.core.showToast
import com.damai.mygithubuser.data.model.UserSearchModel
import com.damai.mygithubuser.databinding.ActivityUserDetailBinding
import com.damai.mygithubuser.presentation.detail.adapter.UserDetailsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
class UserDetailActivity : BaseActivity<UserDetailViewModel>(), UserDetailView,
    ViewDataBindingOwner<ActivityUserDetailBinding> {
    private lateinit var userDetailsAdapter: UserDetailsAdapter

    override var originalBinding: ActivityUserDetailBinding? = null
    override val layoutResourceId: Int = R.layout.activity_user_detail
    override val viewModel: UserDetailViewModel by viewModel()

    companion object {
        private const val BUNDLE_DATA = "bundleData"

        fun generateIntentForResult(
            context: Context,
            data: UserSearchModel
        ): Intent {
            return Intent(context, UserDetailActivity::class.java).apply {
                putExtra(BUNDLE_DATA, data)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val headerData = intent.getParcelableExtra<UserSearchModel>(BUNDLE_DATA)

        setupRvUserDetailsAdapter()
        observeUserDetailListData()
        observeRepoListError()

        headerData?.let {
            val list = mutableListOf<Any>()
            list.add(it)
            viewModel.infoAndRepoListResponse.value = list.toList()

            val urlUri = try {
                Uri.parse(it.url)
            } catch (e: NullPointerException) {
                null
            }
            urlUri?.lastPathSegment?.let { username ->
                viewModel.getRepoList(username = username)
            }
        }
    }

    private fun observeUserDetailListData() {
        observeData(viewModel.infoAndRepoListResponse) { result ->
            result?.let {
                if (!it.isNullOrEmpty()) {
                    userDetailsAdapter.submitList(it)
                }
            }
        }
    }

    private fun observeRepoListError() {
        observeData(viewModel.isError) { result ->
            when (result) {
                true -> showToast(getString(R.string.error_text_api_hit))
                else -> {}
            }
        }
    }

    private fun setupRvUserDetailsAdapter() {
        binding.rvUserDetails.apply {
            userDetailsAdapter = UserDetailsAdapter()
            layoutManager = LinearLayoutManager(
                this@UserDetailActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = userDetailsAdapter
        }
    }
}