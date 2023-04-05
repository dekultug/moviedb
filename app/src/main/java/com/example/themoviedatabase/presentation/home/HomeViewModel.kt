package com.example.themoviedatabase.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.BaseViewModel
import com.example.themoviedatabase.common.getAppString
import com.example.themoviedatabase.common.thread.FlowResult
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

class HomeViewModel : BaseViewModel() {

    private var _movieTrendingState = MutableStateFlow(FlowResult.newInstance<List<Any>>())
    val movieTrendingState = _movieTrendingState.asStateFlow()

    private var _tvTrendingState = MutableStateFlow(FlowResult.newInstance<List<Any>>())
    val tvTrendingState = _tvTrendingState.asStateFlow()

    private var _personTrendingState = MutableStateFlow(FlowResult.newInstance<List<Any>>())
    val personTrendingState = _personTrendingState.asStateFlow()

    private var repo = RepoFactory.getTrendingImpl()

    init {

    }

    fun getMovieTrending(time: String, type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getTrending(time, type)
                .onStart {
                    _movieTrendingState.loading()
                    _tvTrendingState.loading()
                    _personTrendingState.loading()
                }
                .onException {
                    _movieTrendingState.failure(it)
                    _tvTrendingState.failure(it)
                    _personTrendingState.failure(it)
                }
                .collect {
                    val list = arrayListOf<Any>()
                    if (it != null) {
                        list.addAll(it)
                        list.add("")
                    }
                    when (type) {
                        getAppString(R.string._movie) -> _movieTrendingState.success(list)
                        getAppString(R.string._tv) -> _tvTrendingState.success(list)
                        getAppString(R.string._personal) -> _personTrendingState.success(it?: listOf())
                    }
                }
        }
    }
}
