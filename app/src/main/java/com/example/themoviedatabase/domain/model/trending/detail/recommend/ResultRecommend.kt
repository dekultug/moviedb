package com.example.themoviedatabase.domain.model.trending.detail.recommend

import com.example.themoviedatabase.common.extension.STRING_DEFAULT
import com.example.themoviedatabase.common.extension.domainLinkImage
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class ResultRecommend(
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null,

    @SerializedName("overview")
    @Expose
    var overview: String? = null,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,

    @SerializedName("media_type")
    @Expose
    var mediaType: String? = null,

    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>? = null,

    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null,

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null,

    @SerializedName("video")
    @Expose
    var video: Boolean? = null,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null,

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
) {
    fun getImageRecommend(): String {
        return backdropPath?.domainLinkImage() ?: posterPath?.domainLinkImage() ?: STRING_DEFAULT
    }
}
