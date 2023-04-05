package com.example.themoviedatabase.data.network.list

import com.example.themoviedatabase.AppConfig
import com.example.themoviedatabase.data.network.ApiConfig
import com.example.themoviedatabase.data.network.IApiService
import com.example.themoviedatabase.domain.model.list.CheckMovieExistResponse
import com.example.themoviedatabase.domain.model.list.createlist.CreateListRequest
import com.example.themoviedatabase.domain.model.list.createlist.CreateListResponse
import com.example.themoviedatabase.domain.model.list.movie.MovieRequest
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import retrofit2.Call
import retrofit2.http.*

interface IListServer : IApiService {

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("list")
    fun createList(
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("session_id") session_id: String = AppConfig.sessionResponse?.sessionId!!,
        @Body createListRequest: CreateListRequest
    ): Call<CreateListResponse>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("list/{list_id}/add_item")
    fun addMovie(
        @Path("list_id") list_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("session_id") session_id: String = AppConfig.sessionResponse?.sessionId!!,
        @Body movieRequest: MovieRequest
    ): Call<MovieResponse>

    @GET("list/{list_id}/item_status")
    fun isCheckExits(
        @Path("list_id") listId: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("movie_id") movie_id: Int
    ): Call<CheckMovieExistResponse>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("list/{list_id}/remove_item")
    fun deleteMovieInList(
        @Path("list_id") list_id: Int,
        @Query("api_key") api_key: String = ApiConfig.API_KEY,
        @Query("session_id") session_id: String = AppConfig.sessionResponse?.sessionId!!,
        @Body movieRequest: MovieRequest
    ): Call<MovieResponse>
}
