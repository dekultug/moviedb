package com.example.themoviedatabase.data.repo

import com.example.themoviedatabase.base.repo.BaseRepo
import com.example.themoviedatabase.data.network.invokeApi
import com.example.themoviedatabase.data.network.invokeService
import com.example.themoviedatabase.data.network.trending.ITrendingServer
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import com.example.themoviedatabase.domain.repo.ITrending
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TrendingImpl : ITrending, BaseRepo() {
    override fun getTrending(time: String, mediaType: String): Flow<List<TrendingResponse>?> {
        val server = invokeService(ITrendingServer::class.java)
        return server.getTrending(time_window = time, media_type = mediaType)
            .invokeApi { _, movieTrending ,_->
                val flow = flow {
                    emit(movieTrending.results)
                }
                flow
            }
    }

}
