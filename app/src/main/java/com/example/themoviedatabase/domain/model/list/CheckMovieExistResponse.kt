package com.example.themoviedatabase.domain.model.list

import com.example.themoviedatabase.data.network.IApiResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CheckMovieExistResponse: IApiResponse {

    @SerializedName("item_present")
    @Expose
    var itemPresent: Boolean? = null

}
