package com.damai.mygithubuser.presentation.detail

import androidx.lifecycle.MutableLiveData
import com.damai.mygithubuser.core.BaseViewModel
import com.damai.mygithubuser.core.Resource
import com.damai.mygithubuser.data.model.RequestUserInfoModel
import com.damai.mygithubuser.domain.GetUserRepoListUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
class UserDetailViewModel(
    private val userRepoListUseCase: GetUserRepoListUseCase
) : BaseViewModel() {
    var isError = MutableLiveData(false)
    var infoAndRepoListResponse = MutableLiveData<List<Any>>()

    fun getRepoList(
        userId: Int,
        username: String
    ) {
        val requestModel = RequestUserInfoModel(
            id = userId,
            username = username
        )
        launch {
            userRepoListUseCase(requestModel).collect {
                when (it) {
                    is Resource.Success -> {
                        it.model?.let { dataModel ->
                            if (dataModel.dataList.isNotEmpty()) {
                                var lastData = infoAndRepoListResponse.value?.toMutableList()
                                if (lastData == null) {
                                    lastData = arrayListOf()
                                }
                                dataModel.dataList.forEach { data ->
                                    lastData.add(data)
                                }
                                infoAndRepoListResponse.value = lastData.toList()
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