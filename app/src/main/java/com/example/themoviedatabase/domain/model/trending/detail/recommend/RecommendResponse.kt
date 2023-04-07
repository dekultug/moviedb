package com.example.themoviedatabase.domain.model.trending.detail.recommend

import com.example.themoviedatabase.data.network.IApiResponse
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class RecommendResponse (
    @SerializedName("page")
    @Expose
    var page: Int? = null,

    @SerializedName("results")
    @Expose
    var resultRecommend: List<ResultRecommend>? = null,

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null,

    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
): IApiResponse
