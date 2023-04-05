package com.example.themoviedatabase.domain.repo

import com.example.themoviedatabase.domain.model.authen.SessionResponse
import com.example.themoviedatabase.domain.model.authen.TokenResponse
import kotlinx.coroutines.flow.Flow

interface IAuthen {

    fun createToken(): Flow<TokenResponse?>

    fun createSession(tokenResponse: TokenResponse): Flow<SessionResponse?>

    fun deleteSession(sessionResponse: SessionResponse): Flow<Boolean?>

}
