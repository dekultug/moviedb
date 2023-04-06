package com.example.themoviedatabase.domain.model.trending.detail.actor

import com.example.themoviedatabase.data.network.IApiResponse
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class ActorResponse (
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("cast")
    @Expose
    var cast: List<Cast>? = null
): IApiResponse
