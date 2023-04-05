package com.example.themoviedatabase.data.repo

import com.example.themoviedatabase.base.repo.BaseRepo
import com.example.themoviedatabase.data.network.account.IAccountServer
import com.example.themoviedatabase.data.network.invokeApi
import com.example.themoviedatabase.data.network.invokeService
import com.example.themoviedatabase.domain.model.accout.AccountResponse
import com.example.themoviedatabase.domain.model.favoutite.FavouriteRequest
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.trending.createdlist.CreatedListResponse
import com.example.themoviedatabase.domain.model.watchlist.WatchListRequest
import com.example.themoviedatabase.domain.repo.IAccount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AccountImpl: IAccount, BaseRepo() {
    override fun getAccount(): Flow<AccountResponse?> {
        val server = invokeService(IAccountServer::class.java)
        return server.getAccount().invokeApi { headers, accountResponse,_ ->
            val flow = flow {
                emit(accountResponse)
            }
            flow
        }
    }

    override fun getCreatedList(page: Int): Flow<List<CreatedListResponse>?> {
        val server = invokeService(IAccountServer::class.java)
        return server.getCreatedList(page = page).invokeApi { headers, createdListResponse,_ ->
            val flow = flow {
                emit(createdListResponse.results)
            }
            flow
        }
    }

    override fun addFavoutite(favouriteRequest: FavouriteRequest): Flow<MovieResponse?> {
        val server = invokeService(IAccountServer::class.java)
        return server.addFavourite(favouriteRequest = favouriteRequest).invokeApi { _, body,_ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun addWatchList(watchListRequest: WatchListRequest): Flow<MovieResponse?> {
        val server = invokeService(IAccountServer::class.java)
        return server.addWatchList(watchListRequest = watchListRequest).invokeApi { _, body,_ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }
}
