package com.example.themoviedatabase.domain.repo

import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.moviestate.state.Rated
import com.example.themoviedatabase.domain.model.moviestate.state.rate.StateMovie
import com.example.themoviedatabase.domain.model.trending.detail.actor.ActorResponse
import com.example.themoviedatabase.domain.model.trending.detail.info.TrendingDetailResponse
import com.example.themoviedatabase.domain.model.trending.detail.recommend.RecommendResponse
import com.example.themoviedatabase.domain.model.trending.detail.review.UserReviewResponse
import kotlinx.coroutines.flow.Flow

interface ITv {

    fun getStateTv(tvId: Int): Flow<StateMovie?>

    fun rateTv(tvId: Int, rated: Rated): Flow<MovieResponse>

    fun deleteTvRate(tvId: Int): Flow<MovieResponse>

    fun getDetailTvTrending(tvId: Int): Flow<TrendingDetailResponse>

    fun getActorTvInTrending(tvId: Int): Flow<ActorResponse>

    fun getListTvReview(tvId: Int): Flow<UserReviewResponse>

    fun getRecommendTVTrending(tvId: Int): Flow<RecommendResponse>

}
