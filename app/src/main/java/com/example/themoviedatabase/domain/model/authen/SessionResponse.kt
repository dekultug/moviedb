package com.example.themoviedatabase.domain.model.authen

import com.example.themoviedatabase.data.network.IApiResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SessionResponse : IApiResponse {

    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("session_id")
    @Expose
    var sessionId: String? = null

}
