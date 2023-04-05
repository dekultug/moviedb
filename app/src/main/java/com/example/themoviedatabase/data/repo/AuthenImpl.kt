package com.example.themoviedatabase.data.repo

import com.example.themoviedatabase.base.repo.BaseRepo
import com.example.themoviedatabase.data.network.authen.IAuthServer
import com.example.themoviedatabase.data.network.invokeApi
import com.example.themoviedatabase.data.network.invokeService
import com.example.themoviedatabase.domain.model.authen.SessionResponse
import com.example.themoviedatabase.domain.model.authen.TokenResponse
import com.example.themoviedatabase.domain.repo.IAuthen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthenImpl : IAuthen, BaseRepo() {
    override fun createToken(): Flow<TokenResponse?> {
        val server = invokeService(IAuthServer::class.java)
        return server.getToken().invokeApi { _, body,_ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun createSession(tokenResponse: TokenResponse): Flow<SessionResponse?> {
        val server = invokeService(IAuthServer::class.java)
        return server.createSession(tokenResponse = tokenResponse).invokeApi { _, body,_ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun deleteSession(sessionResponse: SessionResponse): Flow<Boolean?> {
        val server = invokeService(IAuthServer::class.java)
        return server.deleteSession(sessionResponse = sessionResponse).invokeApi { _, _ ,_->
            val flow = flow {
                emit(true)
            }
            flow
        }
    }
}
