package com.example.themoviedatabase.presentation.home.more

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.base.BaseViewModel
import com.example.themoviedatabase.common.BOOLEAN_DEFAULT
import com.example.themoviedatabase.common.FLOAT_DEFAULT
import com.example.themoviedatabase.common.INT_DEFAULT
import com.example.themoviedatabase.common.thread.FlowResult
import com.example.themoviedatabase.data.network.ApiConfig
import com.example.themoviedatabase.domain.model.favoutite.FavouriteRequest
import com.example.themoviedatabase.domain.model.moviestate.state.Rated
import com.example.themoviedatabase.domain.model.moviestate.state.rate.StateMovie
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import com.example.themoviedatabase.domain.model.watchlist.WatchListRequest
import com.example.themoviedatabase.factory.RepoFactory
import com.google.gson.Gson
import failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import loading
import onException
import success

open class MoreViewModel : BaseViewModel() {

    private val repoMovie = RepoFactory.getMovieImpl()
    private val repoAccount = RepoFactory.getAccountImpl()
    private val repoTv = RepoFactory.getTvImpl()

    private var _movieState = MutableStateFlow(FlowResult.newInstance<StateMovie>())
    val movieState = _movieState.asStateFlow()

    private var _tvState = MutableStateFlow(FlowResult.newInstance<StateMovie>())
    val tvState = _tvState.asStateFlow()

    private var _markFavouriteState = MutableStateFlow(FlowResult.newInstance<Boolean>())
    val markFavouriteState = _markFavouriteState.asStateFlow()

    private var _markWatchListState = MutableStateFlow(FlowResult.newInstance<Boolean>())
    val markWatchListState = _markWatchListState.asStateFlow()

    var itemMovie: TrendingResponse? = null

    var isFavourite = false

    var isWatchList = false

    var rateValues = -1f

    init {

    }

    fun getStateMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            repoMovie.getStateMovie(itemMovie?.id ?: INT_DEFAULT).onStart {
                _movieState.loading()
            }.onException {
                _movieState.failure(it)
            }.collect {
                if (it == null) return@collect
                isFavourite = it.favorite ?: BOOLEAN_DEFAULT
                isWatchList = it.watchlist ?: BOOLEAN_DEFAULT
                rateValues = if (it.rate !is Boolean) {
                    val gson = Gson()
                    gson.fromJson(it.rate.toString(), Rated::class.java).value
                        ?: FLOAT_DEFAULT
                } else {
                    0f
                }
                _movieState.success(it)
            }
        }
    }

    fun getStateTv() {
        viewModelScope.launch(Dispatchers.IO) {
            repoTv.getStateTv(itemMovie?.id ?: INT_DEFAULT).onStart {
                _tvState.loading()
            }.onException {
                _tvState.failure(it)
            }.collect {
                if (it == null) return@collect
                isFavourite = it.favorite ?: BOOLEAN_DEFAULT
                isWatchList = it.watchlist ?: BOOLEAN_DEFAULT
                rateValues = if (it.rate !is Boolean) {
                    val gson = Gson()
                    gson.fromJson(it.rate.toString(), Rated::class.java).value
                        ?: FLOAT_DEFAULT
                } else {
                    0f
                }
                Log.d("tunglvv", "getStateTv: ${it.favorite}")
                _tvState.success(it)
            }
        }
    }

    fun markFavourite() {
        viewModelScope.launch(Dispatchers.IO) {

            val favouriteRequest = FavouriteRequest(
                mediaId = itemMovie?.id,
                mediaType = itemMovie?.mediaType,
                favorite = isFavourite
            )

            repoAccount.addFavoutite(favouriteRequest)
                .onStart {
                    _markFavouriteState.loading()
                }
                .onException {
                    _markFavouriteState.failure(it)
                }
                .collect {
                    if (it == null) return@collect

                    when (it.statusCode) {
                        ApiConfig.ADD_MARK_SUCCESS -> _markFavouriteState.success(true)
                        ApiConfig.DELETE_MARK_SUCCESS -> _markFavouriteState.success(false)
                    }
                }
        }
    }

    fun markWatchList() {
        viewModelScope.launch(Dispatchers.IO) {

            val watchListRequest = WatchListRequest(
                mediaId = itemMovie?.id,
                mediaType = itemMovie?.mediaType,
                watchlist = isWatchList
            )

            repoAccount.addWatchList(watchListRequest)
                .onStart {
                    _markWatchListState.loading()
                }
                .onException {
                    _markWatchListState.failure(it)
                }
                .collect {
                    if (it == null) return@collect

                    when (it.statusCode) {
                        ApiConfig.ADD_MARK_SUCCESS -> _markWatchListState.success(true)
                        ApiConfig.DELETE_MARK_SUCCESS -> _markWatchListState.success(false)
                    }
                }
        }
    }
}
