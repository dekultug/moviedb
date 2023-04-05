package com.example.themoviedatabase.domain.model.favoutite

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FavouriteRequest(

    @SerializedName("media_type")
    @Expose
    var mediaType: String? = null,

    @SerializedName("media_id")
    @Expose
    var mediaId: Int? = null,

    @SerializedName("favorite")
    @Expose
    var favorite: Boolean? = null
)
