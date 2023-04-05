package com.example.themoviedatabase.domain.repo

import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.moviestate.state.Rated
import com.example.themoviedatabase.domain.model.moviestate.state.rate.StateMovie
import kotlinx.coroutines.flow.Flow

interface IMovie {

    fun getStateMovie(movieId: Int): Flow<StateMovie?>

    fun getStateTv(tvId: Int):  Flow<StateMovie?>

    fun rateMovie(movieId: Int, rated: Rated): Flow<MovieResponse>

    fun rateTv(tvId: Int, rated: Rated): Flow<MovieResponse>

    fun deleteRate(movieId: Int): Flow<MovieResponse>

    fun deleteTvRate(tvId: Int): Flow<MovieResponse>

}
