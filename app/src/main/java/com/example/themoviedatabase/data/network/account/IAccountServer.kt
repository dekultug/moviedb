package com.example.themoviedatabase.data.network.account

import com.example.themoviedatabase.AppConfig
import com.example.themoviedatabase.data.network.ApiConfig
import com.example.themoviedatabase.data.network.IApiService
import com.example.themoviedatabase.domain.model.accout.detail.AccountResponse
import com.example.themoviedatabase.domain.model.favoutite.FavouriteRequest
import com.example.themoviedatabase.domain.model.list.alllist.CreatedListMainResponse
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.trending.movie.TrendingMainResponse
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import com.example.themoviedatabase.domain.model.watchlist.WatchListRequest
import retrofit2.Call
import retrofit2.http.*

interface IAccountServer : IApiService {

    @GET("account/{account_id}/lists")
    fun getCreatedList(
        @Path("account_id") accountId: Int = AppConfig.account?.id!!,
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Query("session_id") sessionId: String = AppConfig.sessionResponse?.sessionId!!,
        @Query("page") page: Int = 1
    ): Call<CreatedListMainResponse>

    @GET("account")
    fun getAccount(
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Query("session_id") sessionId: String = AppConfig.sessionResponse?.sessionId!!,
    ): Call<AccountResponse>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("account/{account_id}/favorite")
    fun addFavourite(
        @Path("account_id") accountId: Int = AppConfig.account?.id!!,
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Query("session_id") sessionId: String = AppConfig.sessionResponse?.sessionId!!,
        @Body favouriteRequest: FavouriteRequest
    ): Call<MovieResponse>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("account/{account_id}/watchlist")
    fun addWatchList(
        @Path("account_id") accountId: Int = AppConfig.account?.id!!,
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Query("session_id") sessionId: String = AppConfig.sessionResponse?.sessionId!!,
        @Body watchListRequest: WatchListRequest
    ): Call<MovieResponse>

    // movie
    @GET("account/{account_id}/favorite/movies")
    fun getFavouriteList(
        @Path("account_id") account_id: Int = AppConfig.account?.id!!,
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Query("session_id") sessionId: String = AppConfig.sessionResponse?.sessionId!!,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sort_by: String = "created_at.asc",
        @Query("page") page: Int = 1
    ): Call<TrendingMainResponse>

    @GET("account/{account_id}/watchlist/movies")
    fun getWatchList(
        @Path("account_id") account_id: Int= AppConfig.account?.id!!,
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Query("session_id") sessionId: String = AppConfig.sessionResponse?.sessionId!!,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sort_by: String = "created_at.asc",
        @Query("page") page: Int = 1
    ): Call<TrendingMainResponse>

    @GET("account/{account_id}/rated/movies")
    fun getRateList(
        @Path("account_id") account_id: Int= AppConfig.account?.id!!,
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Query("session_id") sessionId: String = AppConfig.sessionResponse?.sessionId!!,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sort_by: String = "created_at.asc",
        @Query("page") page: Int = 1
    ): Call<TrendingMainResponse>

    //tv
    @GET("account/{account_id}/favorite/tv")
    fun getFavouriteTvList(
        @Path("account_id") account_id: Int= AppConfig.account?.id!!,
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Query("session_id") sessionId: String = AppConfig.sessionResponse?.sessionId!!,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sort_by: String = "created_at.asc",
        @Query("page") page: Int = 1
    ): Call<TrendingMainResponse>

    @GET("account/{account_id}/watchlist/tv")
    fun getWatchListTv(
        @Path("account_id") account_id: Int= AppConfig.account?.id!!,
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Query("session_id") sessionId: String = AppConfig.sessionResponse?.sessionId!!,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sort_by: String = "created_at.asc",
        @Query("page") page: Int = 1
    ): Call<TrendingMainResponse>

    @GET("account/{account_id}/rated/tv")
    fun getRateTvList(
        @Path("account_id") account_id: Int= AppConfig.account?.id!!,
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Query("session_id") sessionId: String = AppConfig.sessionResponse?.sessionId!!,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sort_by: String = "created_at.asc",
        @Query("page") page: Int = 1
    ): Call<TrendingMainResponse>
}
