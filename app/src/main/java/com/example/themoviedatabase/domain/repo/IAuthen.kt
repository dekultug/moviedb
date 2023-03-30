package com.example.themoviedatabase.domain.repo

import com.example.themoviedatabase.domain.model.authen.Session
import com.example.themoviedatabase.domain.model.authen.Token
import kotlinx.coroutines.flow.Flow

interface IAuthen {

    fun createToken(): Flow<Token?>

    fun createSession(token: Token): Flow<Session?>

    fun deleteSession(session: Session): Flow<Boolean?>

}
