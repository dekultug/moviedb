package com.example.themoviedatabase

import com.example.themoviedatabase.domain.model.accout.AccountResponse
import com.example.themoviedatabase.domain.model.authen.SessionResponse
import com.example.themoviedatabase.domain.model.authen.TokenResponse

object AppConfig {
    const val ITEM_SIZE = 20
    const val TIME_CONFIG = 10L
    const val RESULT_DATA = 2023
    const val NAME_SHARED_PREFERENCE = "THE MOVIE DATABASE"
    const val IS_LOGIN = "IS_LOGIN"
    const val URL_PERMISSION = "https://www.themoviedb.org/authenticate/"
    const val PERMISSION_ALLOW = "/allow"
    const val PERMISSION_DENY = "/deny"
    const val DOMAIN_LINK_IMAGE = "https://image.tmdb.org/t/p/w220_and_h330_face"
    const val PAGE_LIMIT_DEFAULT = 20

    var tokenResponse: TokenResponse? = null
    var sessionResponse: SessionResponse? = null
    var account: AccountResponse? = null
    var maxPopularity = -1
}
