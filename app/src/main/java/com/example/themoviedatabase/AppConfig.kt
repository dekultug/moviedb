package com.example.themoviedatabase

import com.example.themoviedatabase.domain.model.authen.Session
import com.example.themoviedatabase.domain.model.authen.Token

object AppConfig {
    const val ITEM_SIZE = 20
    const val TIME_CONFIG = 10L
    const val RESULT_DATA = 2023
    const val NAME_SHARED_PREFERENCE = "THE MOVIE DATABASE"
    const val IS_LOGIN = "IS_LOGIN"
    const val URL_PERMISSION = "https://www.themoviedb.org/authenticate/"

    var token: Token? = null
    var session: Session? = null
}
