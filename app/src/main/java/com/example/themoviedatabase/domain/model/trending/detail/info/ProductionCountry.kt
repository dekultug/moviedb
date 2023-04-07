package com.example.themoviedatabase.domain.model.trending.detail.info

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class ProductionCountry (
    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null
)
