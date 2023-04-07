package com.example.themoviedatabase.data.repo

import com.example.themoviedatabase.base.repo.BaseRepo
import com.example.themoviedatabase.data.network.invokeApi
import com.example.themoviedatabase.data.network.invokeService
import com.example.themoviedatabase.data.network.movie.IMovieServer
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.moviestate.state.Rated
import com.example.themoviedatabase.domain.model.moviestate.state.rate.StateMovie
import com.example.themoviedatabase.domain.model.trending.detail.actor.ActorResponse
import com.example.themoviedatabase.domain.model.trending.detail.info.TrendingDetailResponse
import com.example.themoviedatabase.domain.model.trending.detail.recommend.RecommendResponse
import com.example.themoviedatabase.domain.model.trending.detail.review.UserReviewResponse
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

    override fun rateMovie(movieId: Int, rated: Rated): Flow<MovieResponse> {
        val server = invokeService(IMovieServer::class.java)
        return server.rateMovie(movie_id = movieId, rated = rated).invokeApi { _, body, _ ->
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

    override fun getDetailMovieTrending(movieId: Int): Flow<TrendingDetailResponse> {
        val server = invokeService(IMovieServer::class.java)
        return server.getDetailMovieTrending(movie_id = movieId).invokeApi { _, body, _ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun getActorInTrending(movieId: Int): Flow<ActorResponse> {
        val server = invokeService(IMovieServer::class.java)
        return server.getActorInTrending(movieId = movieId).invokeApi { _, body, _ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun getListReview(movieId: Int): Flow<UserReviewResponse> {
        val server = invokeService(IMovieServer::class.java)
        return server.getListReview(movieId = movieId).invokeApi { _, body, _ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun getRecommendTrending(movieId: Int): Flow<RecommendResponse> {
        val server = invokeService(IMovieServer::class.java)
        return server.getRecommendTrending(movieId = movieId).invokeApi { _, body, _ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }
}
