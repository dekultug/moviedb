package com.example.themoviedatabase.data.network.movie

import com.example.themoviedatabase.AppConfig
import com.example.themoviedatabase.data.network.ApiConfig
import com.example.themoviedatabase.data.network.IApiService
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.moviestate.state.Rated
import com.example.themoviedatabase.domain.model.moviestate.state.rate.StateMovie
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
}
