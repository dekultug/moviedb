package com.example.themoviedatabase.data.repo

import com.example.themoviedatabase.base.repo.BaseRepo
import com.example.themoviedatabase.data.network.invokeApi
import com.example.themoviedatabase.data.network.invokeService
import com.example.themoviedatabase.data.network.movie.IMovieServer
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.moviestate.state.Rated
import com.example.themoviedatabase.domain.model.moviestate.state.rate.StateMovie
import com.example.themoviedatabase.domain.repo.IMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieImpl : IMovie, BaseRepo() {
    override fun getStateMovie(movieId: Int): Flow<StateMovie?> {
        val server = invokeService(IMovieServer::class.java)
        return try {
            val flow = flow {
                var response = StateMovie()
                server.getStateMovie(movie_id = movieId).invokeApi { _, body, json ->
                    response = body
                }
                emit(response)
            }
            flow
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getStateTv(tvId: Int): Flow<StateMovie?> {
        val server = invokeService(IMovieServer::class.java)
        return try {
            val flow = flow {
                var response = StateMovie()
                server.getStateTv(tv_id = tvId).invokeApi { _, body, json ->
                    response = body
                }
                emit(response)
            }
            flow
        } catch (e: Exception) {
            throw e
        }
    }

    override fun rateMovie(movieId: Int, rated: Rated): Flow<MovieResponse> {
        val server = invokeService(IMovieServer::class.java)
        return server.rateMovie(movie_id = movieId, rated = rated).invokeApi { _, body, _ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun rateTv(tvId: Int, rated: Rated): Flow<MovieResponse> {
        val server = invokeService(IMovieServer::class.java)
        return server.rateTv(tv_id = tvId, rated = rated).invokeApi { _, body, _ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun deleteRate(movieId: Int): Flow<MovieResponse> {
        val server = invokeService(IMovieServer::class.java)
        return server.deleteRate(movie_id = movieId).invokeApi { _, body, _ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun deleteTvRate(tvId: Int): Flow<MovieResponse> {
        val server = invokeService(IMovieServer::class.java)
        return server.deleteTvRate(tv_id = tvId).invokeApi { _, body, _ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }
}
