package com.example.themoviedatabase.domain.model.list.movie

import com.example.themoviedatabase.data.network.IApiResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResponse: IApiResponse {

    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("status_code")
    @Expose
    var statusCode: Int? = null

    @SerializedName("status_message")
    @Expose
    var statusMessage: String? = null
}
