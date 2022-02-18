package com.damai.mygithubuser.presentation

import androidx.lifecycle.MutableLiveData
import com.damai.mygithubuser.core.BaseViewModel
import com.damai.mygithubuser.core.Resource
import com.damai.mygithubuser.data.model.UserSearchListModel
import com.damai.mygithubuser.domain.GetUserListUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
class MainPageViewModel(
    private val getUserListUseCase: GetUserListUseCase
) : BaseViewModel() {
    val isError = MutableLiveData(false)
    val userListResponse = MutableLiveData<UserSearchListModel>()

    fun getUserList() {
        launch {
            getUserListUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        it.model?.let { dataModel ->
                            if (dataModel.dataList.isNotEmpty()) {
                                var lastData = userListResponse.value?.dataList?.toMutableList()
                                if (lastData == null) {
                                    lastData = arrayListOf()
                                }
                                lastData.addAll(dataModel.dataList)
                                userListResponse.value = UserSearchListModel(
                                    dataList = lastData
                                )
                            }
                        }
                    }
                    is Resource.Error -> {
                        isError.value = true
                    }
                    else -> {}
                }
            }
        }
    }
}