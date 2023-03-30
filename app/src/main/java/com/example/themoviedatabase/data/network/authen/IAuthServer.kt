package com.example.themoviedatabase.data.network.authen

import com.example.themoviedatabase.data.network.ApiConfig
import com.example.themoviedatabase.data.network.IApiService
import com.example.themoviedatabase.domain.model.authen.Session
import com.example.themoviedatabase.domain.model.authen.DeleteSessions
import com.example.themoviedatabase.domain.model.authen.Token
import retrofit2.Call
import retrofit2.http.*

interface IAuthServer : IApiService {

    @GET("authentication/token/new")
    fun getToken(@Query("api_key") apiKey: String = ApiConfig.API_KEY): Call<Token>

    @POST("authentication/session/new")
    fun createSession(
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Body token: Token
    ): Call<Session>

    @DELETE("authentication/session")
    fun deleteSession(
        @Query("api_key") apiKey: String = ApiConfig.API_KEY,
        @Body session: Session
    ): Call<DeleteSessions>

}
