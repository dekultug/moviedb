package com.example.themoviedatabase.domain.repo

import com.example.themoviedatabase.domain.model.accout.AccountResponse
import com.example.themoviedatabase.domain.model.favoutite.FavouriteRequest
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.trending.createdlist.CreatedListResponse
import com.example.themoviedatabase.domain.model.watchlist.WatchListRequest
import kotlinx.coroutines.flow.Flow

interface IAccount {

    fun getAccount(): Flow<AccountResponse?>

    fun getCreatedList(page: Int): Flow<List<CreatedListResponse>?>

    fun addFavoutite(favouriteRequest: FavouriteRequest): Flow<MovieResponse?>

    fun addWatchList(watchListRequest: WatchListRequest): Flow<MovieResponse?>
}
