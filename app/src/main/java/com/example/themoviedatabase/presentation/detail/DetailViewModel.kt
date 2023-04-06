package com.example.themoviedatabase.presentation.detail

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.base.BaseViewModel
import com.example.themoviedatabase.common.thread.FlowResult
import com.example.themoviedatabase.domain.model.trending.detail.actor.ActorResponse
import com.example.themoviedatabase.domain.model.trending.detail.info.BelongsToCollection
import com.example.themoviedatabase.domain.model.trending.detail.info.TrendingDetailResponse
import com.example.themoviedatabase.domain.model.trending.detail.recommend.RecommendResponse
import com.example.themoviedatabase.domain.model.trending.detail.review.UserReviewResponse
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

    private var _detailTrendingState = MutableStateFlow(FlowResult.newInstance<List<Any>>())
    val detailTrendingState = _detailTrendingState.asStateFlow()

    private var repo = RepoFactory.getMovieImpl()

    private var dataOfPartTrending: BelongsToCollection? = null

    var idTrending: TrendingResponse? = null

    init {

    }

    fun getDetailTrending(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = mutableListOf<Any>()

            // info
            repo.getDetailMovieTrending(id)
                .onException {
                    _detailTrendingState.failure(it)
                }
                .collect {
                    dataOfPartTrending = it.belongsToCollection
                    list.add(it)
                }

            // actor
            repo.getActorInTrending(id)
                .onException {
                    _detailTrendingState.failure(it)
                }
                .collect {
                    list.add(it)
                }

            // review
            repo.getListReview(id)
                .onException {
                    _detailTrendingState.failure(it)
                }
                .collect {
                    list.add(it)
                }

            // part of trending
            dataOfPartTrending?.let {
                list.add(it)
            }

            // recommend
            repo.getRecommendTrending(id)
                .onException {
                    _detailTrendingState.failure(it)
                }
                .collect {
                    list.add(it)
                }

            _detailTrendingState.success(list)
        }
    }
}
