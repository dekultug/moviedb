package com.example.themoviedatabase.presentation.widget.searchview

interface ISearchListener {
    fun onTextChange(charSequence: CharSequence)
    fun onCloseSearch(){}
}
