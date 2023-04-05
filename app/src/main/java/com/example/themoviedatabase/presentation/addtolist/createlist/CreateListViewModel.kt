package com.example.themoviedatabase.presentation.addtolist.createlist

import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.base.BaseViewModel
import com.example.themoviedatabase.common.thread.FlowResult
import com.example.themoviedatabase.domain.model.list.createlist.CreateListRequest
import com.example.themoviedatabase.domain.model.list.createlist.CreateListResponse
import com.example.themoviedatabase.factory.RepoFactory
import failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import loading
import onException
import success

class CreateListViewModel : BaseViewModel() {

    private var _createListState = MutableStateFlow(FlowResult.newInstance<CreateListResponse>())
    val createListState = _createListState.asStateFlow()

    private val repo = RepoFactory.getListImpl()

    init {

    }

    fun createList(createListRequest: CreateListRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.createList(createListRequest)
                .onStart {
                    _createListState.loading()
                }
                .onException {
                    _createListState.failure(it)
                }
                .collect {
                    if (it != null) {
                        _createListState.success(it)
                    }
                }
        }
    }
}
