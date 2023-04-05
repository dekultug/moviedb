package com.example.themoviedatabase.presentation.home.rate

import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.base.BaseViewModel
import com.example.themoviedatabase.common.INT_DEFAULT
import com.example.themoviedatabase.common.thread.FlowResult
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.moviestate.state.Rated
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
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

class RateViewModel : BaseViewModel() {

    private val repoMovie = RepoFactory.getMovieImpl()

    private val _createRateState = MutableStateFlow(FlowResult.newInstance<MovieResponse>())
    val createRateState = _createRateState.asStateFlow()

    private val _rateTvState = MutableStateFlow(FlowResult.newInstance<MovieResponse>())
    val rateTvState = _rateTvState.asStateFlow()

    private var _deleteRateState = MutableStateFlow(FlowResult.newInstance<MovieResponse>())
    val deleteRateState = _deleteRateState.asStateFlow()

    private var _deleteTvRateState = MutableStateFlow(FlowResult.newInstance<MovieResponse>())
    val deleteTvRateState = _deleteTvRateState.asStateFlow()

    var itemMovie: TrendingResponse? = null

    var valuesRate = 0

    init {

    }

    fun createRate(valuesRate: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            val ratedRequest = Rated(value = (valuesRate * 2).toFloat())

            repoMovie.rateMovie(movieId = itemMovie?.id ?: INT_DEFAULT, rated = ratedRequest)
                .onStart {
                    _createRateState.loading()
                }
                .onException {
                    _createRateState.failure(it)
                }
                .collect {
                    this@RateViewModel.valuesRate = (ratedRequest.value?.toInt() ?: INT_DEFAULT) / 2
                    _createRateState.success(it)
                }
        }
    }

    fun rateTv(valuesRate: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            val ratedRequest = Rated(value = (valuesRate * 2).toFloat())

            repoMovie.rateTv(tvId = itemMovie?.id ?: INT_DEFAULT, rated = ratedRequest)
                .onStart {
                    _rateTvState.loading()
                }
                .onException {
                    _rateTvState.failure(it)
                }
                .collect {
                    this@RateViewModel.valuesRate = (ratedRequest.value?.toInt() ?: INT_DEFAULT) / 2
                    _rateTvState.success(it)
                }
        }
    }

    fun deleteRate() {
        viewModelScope.launch(Dispatchers.IO) {

            repoMovie.deleteRate(movieId = itemMovie?.id ?: INT_DEFAULT)
                .onStart {
                    _deleteRateState.loading()
                }
                .onException {
                    _deleteRateState.failure(it)
                }
                .collect {
                    this@RateViewModel.valuesRate = 0
                    _deleteRateState.success(it)
                }
        }
    }

    fun deleteTvRate() {
        viewModelScope.launch(Dispatchers.IO) {

            repoMovie.deleteTvRate(tvId = itemMovie?.id ?: INT_DEFAULT)
                .onStart {
                    _deleteTvRateState.loading()
                }
                .onException {
                    _deleteTvRateState.failure(it)
                }
                .collect {
                    this@RateViewModel.valuesRate = 0
                    _deleteTvRateState.success(it)
                }
        }
    }
}
