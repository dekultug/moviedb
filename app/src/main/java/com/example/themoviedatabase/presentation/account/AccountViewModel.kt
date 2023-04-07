package com.example.themoviedatabase.presentation.account

import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.common.thread.FlowResult
import com.example.themoviedatabase.domain.model.list.alllist.CreatedListResponse
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import com.example.themoviedatabase.presentation.home.more.MoreViewModel
import data
import failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import loading
import onException
import success

class AccountViewModel : MoreViewModel() {

    private var _createdListResponseState = MutableStateFlow(FlowResult.newInstance<List<CreatedListResponse>>())
    val createdList = _createdListResponseState.asStateFlow()

    private var _favouriteListState = MutableStateFlow(FlowResult.newInstance<List<TrendingResponse>>())
    val favouriteListState = _favouriteListState.asStateFlow()

    private var _watchListState = MutableStateFlow(FlowResult.newInstance<List<TrendingResponse>>())
    val watchListState = _watchListState.asStateFlow()

    private var _rateListState = MutableStateFlow(FlowResult.newInstance<List<TrendingResponse>>())
    val rateListState = _rateListState.asStateFlow()

    var mediaType: String = "movie"

    init {

    }

    fun getCreatedList() {
        viewModelScope.launch(Dispatchers.IO) {
            repoAccount.getCreatedList().onStart {
                _createdListResponseState.loading()
            }.onException {
                _createdListResponseState.failure(it)
            }.collect {
                if (it != null) {
                    _createdListResponseState.success(it)
                }
            }
        }
    }

    // favourite

    fun getFavouriteMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            repoAccount.getFavouriteList().onException {
                _favouriteListState.failure(it)
            }.collect {
                if (it == null) return@collect
                it.map {
                    it.isFavourite = true
                    it.inputType = TrendingResponse.INPUT_TYPE.FAVOURITE
                    it.isRate = false
                    it.isWatchList = false
                }
                _favouriteListState.success(it)
            }
        }
    }

    fun getFavouriteTvList() {
        viewModelScope.launch(Dispatchers.IO) {
            repoAccount.getFavouriteTvList().onException {
                _favouriteListState.failure(it)
            }.collect {
                if (it == null) return@collect
                it.map {
                    it.isFavourite = true
                    it.inputType = TrendingResponse.INPUT_TYPE.FAVOURITE
                    it.isRate = false
                    it.isWatchList = false
                }
                _favouriteListState.success(it)
            }
        }
    }

    fun updateFavouriteList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = _favouriteListState.data()?.toMutableList()
            var position = list?.indexOfFirst {
                it.id == itemMovie?.id
            }
            if (position != null && position > -1) {
                list?.removeAt(position)
            }
            _favouriteListState.success(list ?: listOf())
        }
    }

    // watch list
    fun getWatchList() {
        viewModelScope.launch(Dispatchers.IO) {
            repoAccount.getWatchList().onException {
                _watchListState.failure(it)
            }.collect {
                if (it == null) return@collect
                it.map {
                    it.isFavourite = false
                    it.inputType = TrendingResponse.INPUT_TYPE.WATCHLIST
                    it.isRate = false
                    it.isWatchList = true
                }
                _watchListState.success(it)
            }
        }
    }

    fun getWatchListTv() {
        viewModelScope.launch(Dispatchers.IO) {
            repoAccount.getWatchListTv().onException {
                _watchListState.failure(it)
            }.collect {
                if (it == null) return@collect
                it.map {
                    it.isFavourite = false
                    it.inputType = TrendingResponse.INPUT_TYPE.WATCHLIST
                    it.isRate = false
                    it.isWatchList = true
                }
                _watchListState.success(it)
            }
        }
    }

    fun updateWatchList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = _watchListState.data()?.toMutableList()
            var position = list?.indexOfFirst {
                it.id == itemMovie?.id
            }
            if (position != null && position > -1) {
                list?.removeAt(position)
            }
            _watchListState.success(list ?: listOf())
        }
    }

    // rate
    fun getRateList() {
        viewModelScope.launch(Dispatchers.IO) {
            repoAccount.getRateList().onException {
                _rateListState.failure(it)
            }.collect{
                if (it == null) return@collect
                it.map {
                    it.isFavourite = false
                    it.inputType = TrendingResponse.INPUT_TYPE.RATE
                    it.isRate = true
                    it.isWatchList = false
                }
                _rateListState.success(it)
            }
        }
    }

    fun getRateTvList() {
        viewModelScope.launch(Dispatchers.IO) {
            repoAccount.getRateTvList().onException {
                _rateListState.failure(it)
            }.collect{
                if (it == null) return@collect
                it.map {
                    it.isFavourite = false
                    it.inputType = TrendingResponse.INPUT_TYPE.RATE
                    it.isRate = true
                    it.isWatchList = false
                }
                _rateListState.success(it)
            }
        }
    }
}
