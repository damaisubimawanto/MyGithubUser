package com.damai.mygithubuser.presentation.main

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.damai.mygithubuser.R
import com.damai.mygithubuser.core.BaseActivity
import com.damai.mygithubuser.core.ViewDataBindingOwner
import com.damai.mygithubuser.core.showToast
import com.damai.mygithubuser.data.model.UserSearchModel
import com.damai.mygithubuser.databinding.ActivityMainBinding
import com.damai.mygithubuser.presentation.detail.UserDetailActivity
import com.damai.mygithubuser.presentation.main.adapter.UserSearchAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainPageViewModel>(), ViewDataBindingOwner<ActivityMainBinding>,
    MainPageView, UserSearchAdapter.Callback {
    private lateinit var userSearchAdapter: UserSearchAdapter

    private val openNewActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    override var originalBinding: ActivityMainBinding? = null
    override val layoutResourceId: Int = R.layout.activity_main
    override val viewModel: MainPageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRvUserSearchAdapter()
        observeUserListData()
        observeUserListError()
        observeNotifyIndex()
        observeLoadingCounter()

        viewModel.getUserList()
    }

    /**
     * Callback from UserSearchAdapter.Callback.
     */
    override fun needFetchData(id: Int, username: String) {
        viewModel.getUserInfo(
            id = id,
            username = username
        )
    }

    /**
     * Callback from UserSearchAdapter.Callback.
     */
    override fun onItemClicked(data: UserSearchModel) {
        openNewActivity.launch(UserDetailActivity.generateIntentForResult(
            context = this,
            data = data
        ))
    }

    private fun observeUserListData() {
        observeData(viewModel.userListResponse) { result ->
            result?.let {
                if (it.dataList.isNotEmpty()) {
                    viewModel.cvUserSearchVisibility.value = true
                    userSearchAdapter.submitList(it.dataList)
                }
            }
        }
    }

    private fun observeUserListError() {
        observeData(viewModel.isError) { result ->
            when (result) {
                true -> showToast(getString(R.string.error_text_api_hit))
                else -> {}
            }
        }
    }

    private fun observeNotifyIndex() {
        observeData(viewModel.notifyItemIndex) { result ->
            result?.let {
                if (it > -1) {
                    userSearchAdapter.notifyItemChanged(it)
                }
            }
        }
    }

    private fun observeLoadingCounter() {
        observeData(viewModel.loadingCounter) { result ->
            result?.let {
                binding.cvLoading.visibility = if (it > 0) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        }
    }

    private fun setupRvUserSearchAdapter() {
        binding.rvUserList.apply {
            userSearchAdapter = UserSearchAdapter(mCallback = this@MainActivity)
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = userSearchAdapter
        }
    }
}