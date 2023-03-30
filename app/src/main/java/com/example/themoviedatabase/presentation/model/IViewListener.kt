package com.example.themoviedatabase.presentation.model

interface IViewListener {
    fun onInitial() {}

    fun onLoading() {}

    fun onFailure() {}

    fun onSuccess()
}
