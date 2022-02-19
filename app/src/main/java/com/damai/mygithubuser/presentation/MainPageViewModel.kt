package com.damai.mygithubuser.presentation

import androidx.lifecycle.MutableLiveData
import com.damai.mygithubuser.core.BaseViewModel
import com.damai.mygithubuser.core.Resource
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
    var isError = MutableLiveData(false)
    var cvUserSearchVisibility = MutableLiveData(false)
    var userListResponse = MutableLiveData<UserSearchListModel>()

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
        launch {
            getUserListInfoUseCase(username).collect { result ->
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

                                        userListResponse.value = UserSearchListModel(
                                            dataList = tempData.toList()
                                        )
                                        notifyItemIndex.value = index
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