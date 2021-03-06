package com.damai.mygithubuser.presentation.main

import androidx.lifecycle.MutableLiveData
import com.damai.mygithubuser.core.BaseViewModel
import com.damai.mygithubuser.core.DataSource
import com.damai.mygithubuser.core.Resource
import com.damai.mygithubuser.data.model.RequestUserInfoModel
import com.damai.mygithubuser.data.model.SyncData
import com.damai.mygithubuser.data.model.UserSearchListModel
import com.damai.mygithubuser.domain.GetUserListUseCase
import com.damai.mygithubuser.domain.GetUserSearchInfoUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
class MainPageViewModel(
    private val getUserListUseCase: GetUserListUseCase,
    private val getUserListInfoUseCase: GetUserSearchInfoUseCase
) : BaseViewModel() {
    private val syncData = SyncData()
    var notifyItemIndex = MutableLiveData(-1)
    var loadingCounter = MutableLiveData(0)
    var isError = MutableLiveData(false)
    var cvUserSearchVisibility = MutableLiveData(false)
    var userListResponse = MutableLiveData<UserSearchListModel>()

    fun getUserList() {
        launch {
            getUserListUseCase(DataSource.REMOTE).collect {
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
                                    dataList = lastData.toList()
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

    fun getUserInfo(
        id: Int,
        username: String
    ) {
        val request = RequestUserInfoModel(
            id = id,
            username = username
        )
        loadingCounter.value = (loadingCounter.value ?: 0) + 1
        launch {
            getUserListInfoUseCase(request).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.model?.let { dataModel ->
                            synchronized(syncData) {
                                val tempData = userListResponse.value?.dataList?.toMutableList()
                                val index = tempData?.indexOfFirst { data ->
                                    data.id == id
                                } ?: -1
                                if (index > -1) {
                                    tempData!![index].let {
                                        it.displayName = dataModel.displayName
                                        it.nickname = dataModel.nickname
                                        it.jobPosition = dataModel.jobPosition
                                        it.location = dataModel.location
                                        it.email = dataModel.email
                                        it.followers = dataModel.followers
                                        it.following = dataModel.following
                                        it.isDataFetched = true

                                        userListResponse.value = UserSearchListModel(
                                            dataList = tempData.toList()
                                        )
                                        notifyItemIndex.value = index
                                        loadingCounter.value?.let { counter ->
                                            loadingCounter.value = counter - 1
                                        }
                                    }
                                }
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