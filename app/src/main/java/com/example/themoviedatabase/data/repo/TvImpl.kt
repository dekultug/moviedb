package com.example.themoviedatabase.data.repo

import com.example.themoviedatabase.base.repo.BaseRepo
import com.example.themoviedatabase.data.network.invokeApi
import com.example.themoviedatabase.data.network.invokeService
import com.example.themoviedatabase.data.network.movie.IMovieServer
import com.example.themoviedatabase.data.network.tv.ITvServer
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.moviestate.state.Rated
import com.example.themoviedatabase.domain.model.moviestate.state.rate.StateMovie
import com.example.themoviedatabase.domain.model.trending.detail.actor.ActorResponse
import com.example.themoviedatabase.domain.model.trending.detail.info.TrendingDetailResponse
import com.example.themoviedatabase.domain.model.trending.detail.recommend.RecommendResponse
import com.example.themoviedatabase.domain.model.trending.detail.review.UserReviewResponse
import com.example.themoviedatabase.domain.repo.ITv
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TvImpl: ITv,BaseRepo() {
    override fun getStateTv(tvId: Int): Flow<StateMovie?> {
        val server = invokeService(ITvServer::class.java)
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

    override fun rateTv(tvId: Int, rated: Rated): Flow<MovieResponse> {
        val server = invokeService(ITvServer::class.java)
        return server.rateTv(tv_id = tvId, rated = rated).invokeApi { _, body, _ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun deleteTvRate(tvId: Int): Flow<MovieResponse> {
        val server = invokeService(ITvServer::class.java)
        return server.deleteTvRate(tv_id = tvId).invokeApi { _, body, _ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }


    override fun getDetailTvTrending(tvId: Int): Flow<TrendingDetailResponse> {
        val server = invokeService(ITvServer::class.java)
        return server.getDetailTVTrending(tv_id = tvId).invokeApi { _, body, _ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun getActorTvInTrending(tvId: Int): Flow<ActorResponse> {
        val server = invokeService(ITvServer::class.java)
        return server.getActorInTVTrending(tv_id = tvId).invokeApi { _, body, _ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun getListTvReview(tvId: Int): Flow<UserReviewResponse> {
        val server = invokeService(ITvServer::class.java)
        return server.getListReviewTV(tv_id = tvId).invokeApi { _, body, _ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }

    override fun getRecommendTVTrending(tvId: Int): Flow<RecommendResponse> {
        val server = invokeService(ITvServer::class.java)
        return server.getRecommendTrendingTV(tv_id = tvId).invokeApi { _, body, _ ->
            val flow = flow {
                emit(body)
            }
            flow
        }
    }
}
