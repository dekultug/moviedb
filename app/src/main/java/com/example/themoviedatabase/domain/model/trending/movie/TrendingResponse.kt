package com.example.themoviedatabase.domain.model.trending.movie

import android.os.Parcelable
import com.example.themoviedatabase.AppConfig
import com.example.themoviedatabase.common.STRING_DEFAULT
import com.example.themoviedatabase.common.extension.domainLinkImage
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrendingResponse(
    @SerializedName("id") @Expose var id: Int? = null,

    @SerializedName("adult") @Expose var adult: Boolean? = null,

    @SerializedName("backdrop_path") @Expose var backRopPath: String? = null,

    @SerializedName("title") @Expose var title: String? = null,

    @SerializedName("name") @Expose var name: String? = null,

    @SerializedName("original_language") @Expose var originalLanguage: String? = null,

    @SerializedName("original_title") @Expose var originalTitle: String? = null,

    @SerializedName("overview") @Expose var overview: String? = null,

    @SerializedName("poster_path") @Expose var posterPath: String? = null,

    @SerializedName("media_type") @Expose var mediaType: String? = null,

    @SerializedName("genre_ids") @Expose var genreIds: List<Int>? = null,

    @SerializedName("popularity") @Expose var popularity: Float? = null,

    @SerializedName("release_date") @Expose var releaseDate: String? = null,

    @SerializedName("first_air_date") @Expose var releaseDateTv: String? = null,

    @SerializedName("video") @Expose var video: Boolean? = null,

    @SerializedName("vote_average") @Expose var voteAverage: Float? = null,

    @SerializedName("vote_count") @Expose var voteCount: Float? = null,

    @SerializedName("original_name") @Expose var originalName: String? = null,

    @SerializedName("profile_path") @Expose var profilePath: String? = null

) : Parcelable {

    private fun getImage(): String {
        return posterPath ?: profilePath ?: STRING_DEFAULT
    }

    fun getNameMovie(): String {
        return title ?: name ?: originalName ?: STRING_DEFAULT
    }

    fun getActiveDate(): String {
        return releaseDate ?: releaseDateTv ?: STRING_DEFAULT
    }

    fun getImageHome(): String {
        return getImage().domainLinkImage()
    }

    fun getProgress(): Int {
        if (popularity == null) return 0
        return popularity!!.toInt()
    }
}
