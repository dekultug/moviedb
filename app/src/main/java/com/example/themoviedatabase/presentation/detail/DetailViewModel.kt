package com.example.themoviedatabase.presentation.detail

import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.common.thread.FlowResult
import com.example.themoviedatabase.domain.model.trending.detail.info.BelongsToCollection
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import com.example.themoviedatabase.factory.RepoFactory
import com.example.themoviedatabase.presentation.home.more.MoreViewModel
import failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import onException
import success

class DetailViewModel : MoreViewModel() {

    private var _detailMovieTrendingState = MutableStateFlow(FlowResult.newInstance<List<Any>>())
    val detailMovieTrendingState = _detailMovieTrendingState.asStateFlow()

    private var _detailTvTrendingState = MutableStateFlow(FlowResult.newInstance<List<Any>>())
    val detailTvTrendingState = _detailTvTrendingState.asStateFlow()

    private var dataOfPartTrending: BelongsToCollection? = null

    var idTrending: TrendingResponse? = null

    init {

    }

    fun getDetailMovieTrending(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = mutableListOf<Any>()

            // info
            repoMovie.getDetailMovieTrending(id)
                .onException {
                    _detailMovieTrendingState.failure(it)
                }
                .collect {
                    dataOfPartTrending = it.belongsToCollection
                    list.add(it)
                }

            // actor
            repoMovie.getActorInTrending(id)
                .onException {
                    _detailMovieTrendingState.failure(it)
                }
                .collect {
                    list.add(it)
                }

            // review
            repoMovie.getListReview(id)
                .onException {
                    _detailMovieTrendingState.failure(it)
                }
                .collect {
                    list.add(it)
                }

            // part of trending
            dataOfPartTrending?.let {
                list.add(it)
            }

            // recommend
            repoMovie.getRecommendTrending(id)
                .onException {
                    _detailMovieTrendingState.failure(it)
                }
                .collect {
                    list.add(it)
                }

            _detailMovieTrendingState.success(list)
        }
    }

    fun getDetailTvTrending(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val list = mutableListOf<Any>()

            // info
            repoTv.getDetailTvTrending(id)
                .onException {
                    _detailTvTrendingState.failure(it)
                }
                .collect {
                    dataOfPartTrending = it.belongsToCollection
                    list.add(it)
                }

            // actor
            repoTv.getActorTvInTrending(id)
                .onException {
                    _detailTvTrendingState.failure(it)
                }
                .collect {
                    list.add(it)
                }

            // review
            repoTv.getListTvReview(id)
                .onException {
                    _detailTvTrendingState.failure(it)
                }
                .collect {
                    list.add(it)
                }

            // part of trending
            dataOfPartTrending?.let {
                list.add(it)
            }

            // recommend
            repoTv.getRecommendTVTrending(id)
                .onException {
                    _detailTvTrendingState.failure(it)
                }
                .collect {
                    list.add(it)
                }

            _detailTvTrendingState.success(list)
        }
    }
}
