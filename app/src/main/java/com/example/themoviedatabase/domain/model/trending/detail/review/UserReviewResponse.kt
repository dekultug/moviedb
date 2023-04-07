package com.example.themoviedatabase.domain.model.trending.detail.review

import com.example.themoviedatabase.data.network.IApiResponse
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class UserReviewResponse (
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("page")
    @Expose
    var page: Int? = null,

    @SerializedName("results")
    @Expose
    var infoReviews: List<InfoReview>? = null,

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null,

    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
): IApiResponse
