package com.example.themoviedatabase.factory

import com.example.themoviedatabase.data.repo.*
import com.example.themoviedatabase.domain.repo.*
import com.example.themoviedatabase.presentation.widget.filter.FilterDisplayImpl
import com.example.themoviedatabase.presentation.widget.filter.IFilterDisplay

object RepoFactory {
    private val authenImpl by lazy { AuthenImpl() }
    private val trendingImpl by lazy { TrendingImpl() }
    private val filteDisplayImpl by lazy { FilterDisplayImpl() }
    private val accountImpl by lazy { AccountImpl() }
    private val listImpl by lazy { ListImpl() }
    private val movieImpl by lazy { MovieImpl() }

    fun getAuthImpl(): IAuthen {
        return authenImpl
    }

    fun getFilterDisplayImpl(): IFilterDisplay {
        return filteDisplayImpl
    }

    fun getTrendingImpl(): ITrending {
        return trendingImpl
    }

    fun getAccountImpl(): IAccount {
        return accountImpl
    }

    fun getListImpl(): IList {
        return listImpl
    }

    fun getMovieImpl(): IMovie {
        return movieImpl
    }
}
