package com.example.themoviedatabase.domain.model.authen

import com.example.themoviedatabase.data.network.IApiResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TokenResponse : IApiResponse {

    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("request_token")
    @Expose
    var requestToken: String? = null
}
