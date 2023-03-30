package com.example.themoviedatabase.di

import com.example.themoviedatabase.data.repo.AuthenImpl
import com.example.themoviedatabase.domain.repo.IAuthen

object RepoFactory {
    private val authenImpl by lazy { AuthenImpl() }

    fun getAuthImpl(): IAuthen {
        return authenImpl
    }
}
