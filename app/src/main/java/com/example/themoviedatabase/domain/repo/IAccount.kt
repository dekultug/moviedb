package com.example.themoviedatabase.domain.repo

import com.example.themoviedatabase.domain.model.accout.detail.AccountResponse
import com.example.themoviedatabase.domain.model.favoutite.FavouriteRequest
import com.example.themoviedatabase.domain.model.list.alllist.CreatedListResponse
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import com.example.themoviedatabase.domain.model.watchlist.WatchListRequest
import kotlinx.coroutines.flow.Flow

interface IAccount {

    fun getAccount(): Flow<AccountResponse?>

    fun getCreatedList(page: Int = 1): Flow<List<CreatedListResponse>?>

    fun addFavoutite(favouriteRequest: FavouriteRequest): Flow<MovieResponse?>

    fun addWatchList(watchListRequest: WatchListRequest): Flow<MovieResponse?>

    // movie
    fun getFavouriteList(): Flow<List<TrendingResponse>?>

    fun getWatchList(): Flow<List<TrendingResponse>?>

    fun getRateList(): Flow<List<TrendingResponse>?>

    // tv
    fun getFavouriteTvList(): Flow<List<TrendingResponse>?>

    fun getWatchListTv(): Flow<List<TrendingResponse>?>

    fun getRateTvList(): Flow<List<TrendingResponse>?>
}
