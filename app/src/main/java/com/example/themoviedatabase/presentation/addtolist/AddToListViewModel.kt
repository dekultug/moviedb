package com.example.themoviedatabase.presentation.addtolist

import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.base.BaseViewModel
import com.example.themoviedatabase.common.INT_DEFAULT
import com.example.themoviedatabase.common.thread.FlowResult
import com.example.themoviedatabase.domain.model.list.movie.MovieRequest
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.example.themoviedatabase.domain.model.list.alllist.CreatedListResponse
import com.example.themoviedatabase.factory.RepoFactory
import data
import failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import loading
import onException
import success

class AddToListViewModel : BaseViewModel() {
    private val TAG = "AddToListViewModel"
    private var _createdListResponseState = MutableStateFlow(FlowResult.newInstance<List<CreatedListResponse>>())
    val createdList = _createdListResponseState.asStateFlow()

    private var _addMovieState = MutableStateFlow(FlowResult.newInstance<MovieResponse>())
    val addMovieState = _addMovieState.asStateFlow()

    private var _isSelectedState = MutableStateFlow(FlowResult.newInstance<Boolean>())
    val isSelectedState = _isSelectedState.asStateFlow()

    private val repoAccount = RepoFactory.getAccountImpl()
    private val repoList = RepoFactory.getListImpl()

    var createdListResponseSelect: CreatedListResponse? = null

    var movieID: Int? = null

    var movieRequest: MovieRequest? = null

    var listIdMoviePresent = mutableListOf<Int>()

    init {

    }

    private fun checkDataSelected() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = _createdListResponseState.data()?.toMutableList()

            val index = list?.indexOfFirst {
                it.isSelect
            }

            if (index != null && index > -1) {
                _isSelectedState.success(true)
            } else {
                _isSelectedState.success(false)
            }
        }
    }

    fun getCreatedList(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repoAccount.getCreatedList(page)
                .onStart {
                    _createdListResponseState.loading()
                }
                .onException {
                    _createdListResponseState.failure(it)
                }
                .collect {
                    if (it != null) {

                        it.forEachIndexed { i, v ->
                            repoList.checkExitMovie(listId = v.id ?: INT_DEFAULT, movieID = movieID
                                ?: INT_DEFAULT)
                                .onStart {
                                    _isSelectedState.loading()
                                }
                                .onException {
                                    _isSelectedState.failure(it)
                                }
                                .collect {
                                    if (it == null) return@collect
                                    if (it.itemPresent == true) {
                                        listIdMoviePresent.add(v.id ?: INT_DEFAULT)
                                    }
                                }
                        }

                        val list = mutableListOf<CreatedListResponse>()
                        list.addAll(it)

                        list.forEachIndexed { i, v ->
                            if (listIdMoviePresent.contains(v.id)){
                                v.isInList = true
                            }
                        }

                        _createdListResponseState.success(list)
                    }
                }
        }
    }

    fun setItemSelect(item: CreatedListResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = _createdListResponseState.data()?.toMutableList()

            var newItem: CreatedListResponse? = null
            var newPosition = -1

            newItem = list?.find {
                it.id == item.id
            }

            newPosition = list?.indexOfFirst {
                it.id == item.id
            } ?: -1

            if (newPosition != -1) {
                newItem = list?.get(newPosition)
            }

            var oldItem: CreatedListResponse? = null
            var oldPostion = -1

            list?.forEachIndexed { i, v ->
                if (v.isSelect) {
                    oldItem = v
                    oldPostion = i
                }
            }

            if (newPosition != oldPostion) {
                if (newItem != null) {
                    val changeItem = newItem.copy()
                    changeItem.isSelect = true
                    list?.set(newPosition, changeItem)
                }

                if (oldItem != null) {
                    val changeItem = oldItem!!.copy()
                    changeItem.isSelect = false
                    list?.set(oldPostion, changeItem)
                }
            }
            _createdListResponseState.success(list ?: listOf())
        }
    }

    fun addMovie(listId: Int, movieRequest: MovieRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            repoList.addMovie(listId, movieRequest)
                .onStart {
                    _addMovieState.loading()
                }
                .onException {
                    _addMovieState.failure(it)
                }
                .collect {
                    if (it == null) return@collect
                    _addMovieState.success(it)
                }
        }
    }
}
