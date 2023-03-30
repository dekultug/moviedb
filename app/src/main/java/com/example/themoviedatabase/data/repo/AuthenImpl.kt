package com.example.themoviedatabase.data.repo

import com.example.themoviedatabase.base.repo.BaseRepo
import com.example.themoviedatabase.data.network.authen.IAuthServer
import com.example.themoviedatabase.data.network.invokeApi
import com.example.themoviedatabase.data.network.invokeService
import com.example.themoviedatabase.domain.model.authen.Session
import com.example.themoviedatabase.domain.model.authen.Token
import com.example.themoviedatabase.domain.repo.IAuthen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthenImpl : IAuthen, BaseRepo() {
    override fun createToken(): Flow<Token?> {
        val server = invokeService(IAuthServer::class.java)
        return server.getToken().invokeApi { _, body ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun createSession(token: Token): Flow<Session?> {
        val server = invokeService(IAuthServer::class.java)
        return server.createSession(token = token).invokeApi { _, body ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun deleteSession(session: Session): Flow<Boolean?> {
        val server = invokeService(IAuthServer::class.java)
        return server.deleteSession(session = session).invokeApi { _, _ ->
            val flow = flow {
                emit(true)
            }
            flow
        }
    }
}
