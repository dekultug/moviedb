package com.example.themoviedatabase.domain.model.watchlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WatchListRequest(
    @SerializedName("media_type")
    @Expose
    var mediaType: String? = null,

    @SerializedName("media_id")
    @Expose
    var mediaId: Int? = null,

    @SerializedName("watchlist")
    @Expose
    var watchlist: Boolean? = null
)
