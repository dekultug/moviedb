package com.example.themoviedatabase.domain.model.trending.detail.info

import com.example.themoviedatabase.R
import com.example.themoviedatabase.common.extension.STRING_DEFAULT
import com.example.themoviedatabase.common.extension.domainLinkImage
import com.example.themoviedatabase.common.extension.getAppString
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class BelongsToCollection(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null
) {
    fun getImage(): String {
        return backdropPath?.domainLinkImage() ?: posterPath?.domainLinkImage() ?: STRING_DEFAULT
    }

    fun getNameFilm(): String {
        return if (name?.endsWith("Collection") == true) {
            name?.replace("Collection", "")?.trim().toString()
        } else {
            name ?: STRING_DEFAULT
        }
    }
}
