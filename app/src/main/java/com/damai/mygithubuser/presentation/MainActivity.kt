package com.damai.mygithubuser.presentation

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.damai.mygithubuser.R
import com.damai.mygithubuser.core.BaseActivity
import com.damai.mygithubuser.core.ViewDataBindingOwner
import com.damai.mygithubuser.core.showToast
import com.damai.mygithubuser.databinding.ActivityMainBinding
import com.damai.mygithubuser.presentation.adapter.UserSearchAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainPageViewModel>(), ViewDataBindingOwner<ActivityMainBinding>,
    MainPageView {
    private lateinit var userSearchAdapter: UserSearchAdapter

    override var originalBinding: ActivityMainBinding? = null
    override val layoutResourceId: Int = R.layout.activity_main
    override val viewModel: MainPageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRvUserSearchAdapter()
        observeUserListData()
        observeUserListError()

        viewModel.getUserList()
    }

    private fun observeUserListData() {
        observeData(viewModel.userListResponse) { result ->
            result?.let {
                if (it.dataList.isNullOrEmpty()) {

                } else {
                    viewModel.cvUserSearchVisibility.value = true
                    userSearchAdapter.submitList(it.dataList)
                }
            }
        }
    }

    private fun observeUserListError() {
        observeData(viewModel.isError) { result ->
            result?.let {
                when (it) {
                    true -> showToast(getString(R.string.error_text_api_hit))
                    else -> {}
                }
            }
        }
    }

    private fun setupRvUserSearchAdapter() {
        binding.rvUserList.apply {
            userSearchAdapter = UserSearchAdapter()
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = userSearchAdapter
        }
    }
}