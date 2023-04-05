package com.example.themoviedatabase.data.network.authen

import com.example.themoviedatabase.data.network.ApiConfig
import com.example.themoviedatabase.data.network.IApiService
import com.example.themoviedatabase.domain.model.authen.SessionResponse
import com.example.themoviedatabase.domain.model.authen.DeleteSessionsResponse
import com.example.themoviedatabase.domain.model.authen.TokenResponse
import retrofit2.Call
import retrofit2.http.*

interface IAuthServer : IApiService {

    @GET("authentication/token/new")
    fun getToken(@Query("api_key") apiKey: String = ApiConfig.API_KEY): Call<TokenResponse>

    @POST("authentication/session/new")
    fun createSession(
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Body tokenResponse: TokenResponse
    ): Call<SessionResponse>

    @DELETE("authentication/session")
    fun deleteSession(
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Body sessionResponse: SessionResponse
    ): Call<DeleteSessionsResponse>

}
