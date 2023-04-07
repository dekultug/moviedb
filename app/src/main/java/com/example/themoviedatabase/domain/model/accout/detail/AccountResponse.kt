package com.example.themoviedatabase.domain.model.accout.detail

import com.example.themoviedatabase.common.extension.STRING_DEFAULT
import com.example.themoviedatabase.data.network.IApiResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AccountResponse : IApiResponse {

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("iso_639_1")
    @Expose
    var iso_639_1: String? = null

    @SerializedName("iso_3166_1")
    @Expose
    var iso_3166_1: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("include_adult")
    @Expose
    var includeAdult: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null
}
