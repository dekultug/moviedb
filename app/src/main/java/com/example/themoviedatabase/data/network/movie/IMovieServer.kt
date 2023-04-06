package com.example.themoviedatabase.data.network.movie

import com.example.themoviedatabase.AppConfig
import com.example.themoviedatabase.data.network.ApiConfig
import com.example.themoviedatabase.data.network.IApiService
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.moviestate.state.Rated
import com.example.themoviedatabase.domain.model.moviestate.state.rate.StateMovie
import com.example.themoviedatabase.domain.model.trending.detail.actor.ActorResponse
import com.example.themoviedatabase.domain.model.trending.detail.info.TrendingDetailResponse
import com.example.themoviedatabase.domain.model.trending.detail.recommend.RecommendResponse
import com.example.themoviedatabase.domain.model.trending.detail.review.UserReviewResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.*

interface IMovieServer : IApiService {

    @GET("movie/{movie_id}/account_states")
    fun getStateMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("session_id") session_id: String = AppConfig.sessionResponse?.sessionId!!
    ): Call<StateMovie>

    @GET("tv/{tv_id}/account_states")
    fun getStateTv(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("session_id") session_id: String = AppConfig.sessionResponse?.sessionId!!
    ): Call<StateMovie>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("movie/{movie_id}/rating")
    fun rateMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("session_id") session_id: String = AppConfig.sessionResponse?.sessionId!!,
        @Body rated: Rated
    ): Call<MovieResponse>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("tv/{tv_id}/rating")
    fun rateTv(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("session_id") session_id: String = AppConfig.sessionResponse?.sessionId!!,
        @Body rated: Rated
    ): Call<MovieResponse>

    @Headers("Content-Type: application/json;charset=utf-8")
    @DELETE("movie/{movie_id}/rating")
    fun deleteRate(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("session_id") session_id: String = AppConfig.sessionResponse?.sessionId!!,
    ): Call<MovieResponse>

    @Headers("Content-Type: application/json;charset=utf-8")
    @DELETE("tv/{tv_id}/rating")
    fun deleteTvRate(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("session_id") session_id: String = AppConfig.sessionResponse?.sessionId!!,
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovieTrending(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("language") language: String = "en-US"
    ): Call<TrendingDetailResponse>

    @GET("movie/{movie_id}/credits")
    fun getActorInTrending(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("language") language: String = "en-US"
    ): Call<ActorResponse>

    @GET("movie/{movie_id}/reviews")
    fun getListReview(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<UserReviewResponse>

    @GET("movie/{movie_id}/recommendations")
    fun getRecommendTrending(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<RecommendResponse>
}
