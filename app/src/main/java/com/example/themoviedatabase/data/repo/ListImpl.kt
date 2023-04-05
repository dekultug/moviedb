package com.example.themoviedatabase.data.repo

import com.example.themoviedatabase.base.repo.BaseRepo
import com.example.themoviedatabase.data.network.invokeApi
import com.example.themoviedatabase.data.network.invokeService
import com.example.themoviedatabase.data.network.list.IListServer
import com.example.themoviedatabase.domain.model.list.CheckMovieExistResponse
import com.example.themoviedatabase.domain.model.list.movie.MovieRequest
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.list.createlist.CreateListRequest
import com.example.themoviedatabase.domain.model.list.createlist.CreateListResponse
import com.example.themoviedatabase.domain.repo.IList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ListImpl : IList, BaseRepo() {
    override fun createList(createListRequest: CreateListRequest): Flow<CreateListResponse?> {
        val server = invokeService(IListServer::class.java)
        return server.createList(createListRequest = createListRequest).invokeApi { headers, createListResponse,_ ->
            val flow = flow {
                emit(createListResponse)
            }
            flow
        }
    }

    override fun addMovie(listId: Int, movieRequest: MovieRequest): Flow<MovieResponse?> {
        val server = invokeService(IListServer::class.java)
        val flow = flow {
            try {
                var response: MovieResponse? = null
                server.addMovie(list_id = listId, movieRequest = movieRequest).invokeApi { _, body,_ ->
                    response = body
                }
                if (response != null) {
                    emit(response!!)
                }
            } catch (e: Exception) {
                throw e
            }
        }
        return flow
    }

    override fun checkExitMovie(listId: Int, movieID: Int): Flow<CheckMovieExistResponse?> {
        val server = invokeService(IListServer::class.java)
        return server.isCheckExits(listId = listId, movie_id = movieID).invokeApi { headers, body,_ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun removeMovieInList(listId: Int, movieRequest: MovieRequest): Flow<MovieResponse?> {
        val server = invokeService(IListServer::class.java)
        return server.deleteMovieInList(list_id = listId, movieRequest = movieRequest).invokeApi { headers, body,_ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }
}
