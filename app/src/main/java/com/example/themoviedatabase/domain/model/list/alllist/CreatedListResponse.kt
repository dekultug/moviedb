package com.example.themoviedatabase.domain.model.list.alllist

import com.example.themoviedatabase.data.network.IApiResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CreatedListResponse(

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("favorite_count")
    @Expose
    var favoriteCount: Int? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("item_count")
    @Expose
    var itemCount: Int? = null,

    @SerializedName("iso_639_1")
    @Expose
    var iso_639_1: String? = null,

    @SerializedName("list_type")
    @Expose
    var listType: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,

    var isSelect: Boolean = false,

    var isInList: Boolean = false

) : IApiResponse

