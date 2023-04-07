package com.example.themoviedatabase.domain.model.trending.detail.review

import com.example.themoviedatabase.common.extension.STRING_DEFAULT
import com.example.themoviedatabase.common.extension.domainLinkImage
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class AuthorDetails(
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("username")
    @Expose
    var username: String? = null,

    @SerializedName("avatar_path")
    @Expose
    var avatarPath: String? = null,

    @SerializedName("rating")
    @Expose
    var rating: Double? = null
) {
    fun getAvatar(): String {
        return avatarPath?.domainLinkImage() ?: STRING_DEFAULT
    }
}
