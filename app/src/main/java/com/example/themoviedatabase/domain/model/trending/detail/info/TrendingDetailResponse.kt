package com.example.themoviedatabase.domain.model.trending.detail.info

import com.example.themoviedatabase.common.INT_DEFAULT
import com.example.themoviedatabase.common.extension.STRING_DEFAULT
import com.example.themoviedatabase.common.extension.domainLinkImage
import com.example.themoviedatabase.data.network.IApiResponse
import com.example.themoviedatabase.domain.model.trending.detail.info.*
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class TrendingDetailResponse(

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,

    @SerializedName("belongs_to_collection")
    @Expose
    var belongsToCollection: BelongsToCollection? = null,

    @SerializedName("budget")
    @Expose
    var budget: Int? = null,

    @SerializedName("genres")
    @Expose
    var genres: List<Genre>? = null,

    @SerializedName("homepage")
    @Expose
    var homepage: String? = null,

    @SerializedName("imdb_id")
    @Expose
    var imdbId: String? = null,

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("overview")
    @Expose
    var overview: String? = null,

    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,

    @SerializedName("production_companies")
    @Expose
    var productionCompanies: List<ProductionCompany>? = null,

    @SerializedName("production_countries")
    @Expose
    var productionCountries: List<ProductionCountry>? = null,

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null,

    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String? = null,


    @SerializedName("revenue")
    @Expose
    var revenue: Long? = null,

    @SerializedName("runtime")
    @Expose
    var runtime: Int? = null,

    @SerializedName("spoken_languages")
    @Expose
    var spokenLanguages: List<SpokenLanguage>? = null,

    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("tagline")
    @Expose
    var tagline: String? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("video")
    @Expose
    var video: Boolean? = null,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float? = null,

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
) : IApiResponse {
    fun getBackground(): String {
        return backdropPath?.domainLinkImage() ?: STRING_DEFAULT
    }

    fun getImageTrending(): String {
        return posterPath?.domainLinkImage() ?: STRING_DEFAULT
    }

    fun getNameTrending(): String {
        return originalTitle ?: title ?: name ?: STRING_DEFAULT
    }

    fun getProgressUserScore(): Int {
        return if (voteAverage != null) {
            (voteAverage!! * 10).toInt()
        } else {
            INT_DEFAULT
        }
    }

    fun getActiveDate(): String {
        return releaseDate ?: firstAirDate ?: STRING_DEFAULT
    }
}
