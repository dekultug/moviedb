package com.example.themoviedatabase.domain.repo

import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import kotlinx.coroutines.flow.Flow

interface ITrending {

    fun getTrending(time: String, mediaType: String): Flow<List<TrendingResponse>?>

}
