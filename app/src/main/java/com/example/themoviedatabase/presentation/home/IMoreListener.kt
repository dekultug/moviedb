package com.example.themoviedatabase.presentation.home

import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse

interface IMoreListener {
    fun showMoreAction(item: TrendingResponse)
}
