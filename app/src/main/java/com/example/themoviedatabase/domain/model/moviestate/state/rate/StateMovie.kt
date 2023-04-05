package com.example.themoviedatabase.domain.model.moviestate.state.rate

import com.example.themoviedatabase.data.network.IApiResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class StateMovie : IApiResponse {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("favorite")
    @Expose
    var favorite: Boolean? = null

    @SerializedName("watchlist")
    @Expose
    var watchlist: Boolean? = null

    @SerializedName("rated")
    @Expose
    var rate: Any? = null
}
