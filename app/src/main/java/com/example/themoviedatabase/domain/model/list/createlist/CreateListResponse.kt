package com.example.themoviedatabase.domain.model.list.createlist

import com.example.themoviedatabase.data.network.IApiResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateListResponse : IApiResponse {

    @SerializedName("status_code")
    @Expose
    var statusCode: Int? = null

    @SerializedName("status_message")
    @Expose
    var statusMessage: String? = null

    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("list_id")
    @Expose
    var listId: Int? = null
}
