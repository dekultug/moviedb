package com.example.themoviedatabase.data.network.tv

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
import retrofit2.Call
import retrofit2.http.*

interface ITvServer: IApiService {

    @GET("tv/{tv_id}/account_states")
    fun getStateTv(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("session_id") session_id: String = AppConfig.sessionResponse?.sessionId!!
    ): Call<StateMovie>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("tv/{tv_id}/rating")
    fun rateTv(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("session_id") session_id: String = AppConfig.sessionResponse?.sessionId!!,
        @Body rated: Rated
    ): Call<MovieResponse>

    @Headers("Content-Type: application/json;charset=utf-8")
    @DELETE("tv/{tv_id}/rating")
    fun deleteTvRate(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("session_id") session_id: String = AppConfig.sessionResponse?.sessionId!!,
    ): Call<MovieResponse>

    @GET("tv/{tv_id}")
    fun getDetailTVTrending(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("language") language: String = "en-US"
    ): Call<TrendingDetailResponse>

    @GET("tv/{tv_id}/credits")
    fun getActorInTVTrending(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("language") language: String = "en-US"
    ): Call<ActorResponse>

    @GET("tv/{tv_id}/reviews")
    fun getListReviewTV(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<UserReviewResponse>

    @GET("tv/{tv_id}/recommendations")
    fun getRecommendTrendingTV(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<RecommendResponse>
}
