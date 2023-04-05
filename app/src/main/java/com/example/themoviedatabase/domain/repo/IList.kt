package com.example.themoviedatabase.domain.repo

import com.example.themoviedatabase.domain.model.list.CheckMovieExistResponse
import com.example.themoviedatabase.domain.model.list.movie.MovieRequest
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.list.createlist.CreateListRequest
import com.example.themoviedatabase.domain.model.list.createlist.CreateListResponse
import kotlinx.coroutines.flow.Flow

interface IList {

    fun createList(createListRequest: CreateListRequest): Flow<CreateListResponse?>

    fun addMovie(listId: Int, movieRequest: MovieRequest): Flow<MovieResponse?>

    fun checkExitMovie(listId: Int,movieID: Int): Flow<CheckMovieExistResponse?>

    fun removeMovieInList(listId: Int, movieRequest: MovieRequest): Flow<MovieResponse?>
}
