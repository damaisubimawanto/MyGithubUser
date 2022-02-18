package com.damai.mygithubuser.presentation

import android.os.Bundle
import com.damai.mygithubuser.R
import com.damai.mygithubuser.core.BaseActivity
import com.damai.mygithubuser.core.ViewDataBindingOwner
import com.damai.mygithubuser.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainPageViewModel>(), ViewDataBindingOwner<ActivityMainBinding>,
    MainPageView {
    override var originalBinding: ActivityMainBinding? = null
    override val layoutResourceId: Int = R.layout.activity_main
    override val viewModel: MainPageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeUserListData()
        observeUserListError()

        viewModel.getUserList()
    }

    private fun observeUserListData() {
        observeData(viewModel.userListResponse) { result ->
            result?.let {
                if (it.dataList.isNullOrEmpty()) {

                }
            }
        }
    }

    private fun observeUserListError() {
        observeData(viewModel.isError) { result ->
            result?.let {

            }
        }
    }
}