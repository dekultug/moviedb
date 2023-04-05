package com.example.themoviedatabase.data.network.account

import com.example.themoviedatabase.AppConfig
import com.example.themoviedatabase.data.network.ApiConfig
import com.example.themoviedatabase.data.network.IApiService
import com.example.themoviedatabase.domain.model.accout.AccountResponse
import com.example.themoviedatabase.domain.model.favoutite.FavouriteRequest
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.trending.createdlist.CreatedListMainResponse
import com.example.themoviedatabase.domain.model.watchlist.WatchListRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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
}
