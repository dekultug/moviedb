package com.example.themoviedatabase.domain.model.list.movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieRequest{

    @SerializedName("media_id")
    @Expose
    var mediaId: Int? = null

}
