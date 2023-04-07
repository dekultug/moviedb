package com.example.themoviedatabase.domain.repo

import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.moviestate.state.Rated
import com.example.themoviedatabase.domain.model.moviestate.state.rate.StateMovie
import com.example.themoviedatabase.domain.model.trending.detail.actor.ActorResponse
import com.example.themoviedatabase.domain.model.trending.detail.info.TrendingDetailResponse
import com.example.themoviedatabase.domain.model.trending.detail.recommend.RecommendResponse
import com.example.themoviedatabase.domain.model.trending.detail.review.UserReviewResponse
import kotlinx.coroutines.flow.Flow

interface IMovie {

    fun getStateMovie(movieId: Int): Flow<StateMovie?>

    fun rateMovie(movieId: Int, rated: Rated): Flow<MovieResponse>

    fun deleteRate(movieId: Int): Flow<MovieResponse>

    fun getDetailMovieTrending(movieId: Int): Flow<TrendingDetailResponse>

    fun getActorInTrending(movieId: Int): Flow<ActorResponse>

    fun getListReview(movieId: Int): Flow<UserReviewResponse>

    fun getRecommendTrending(movieId: Int): Flow<RecommendResponse>

}
