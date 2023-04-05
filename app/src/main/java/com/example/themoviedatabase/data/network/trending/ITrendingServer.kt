package com.example.themoviedatabase.data.network.trending

import com.example.themoviedatabase.data.network.ApiConfig
import com.example.themoviedatabase.data.network.IApiService
import com.example.themoviedatabase.domain.model.trending.movie.TrendingMainResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ITrendingServer : IApiService {

    @GET("trending/{media_type}/{time_window}")
    fun getTrending(
        @Path("media_type") media_type: String,
        @Path("time_window") time_window: String,
        @Query("api_key") api_key: String = ApiConfig.API_KEY
    ): Call<TrendingMainResponse>

}
