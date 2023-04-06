package com.example.themoviedatabase.domain.model.trending.detail.actor

import com.example.themoviedatabase.common.extension.domainLinkImage
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Cast (

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null,

    @SerializedName("gender")
    @Expose
    var gender: Int? = null,

    @SerializedName("known_for_department")
    @Expose
    var knownForDepartment: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("original_name")
    @Expose
    var originalName: String? = null,

    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null,

    @SerializedName("profile_path")
    @Expose
    var profilePath: String? = null,

    @SerializedName("cast_id")
    @Expose
    var castId: Int? = null,

    @SerializedName("character")
    @Expose
    var character: String? = null,

    @SerializedName("credit_id")
    @Expose
    var creditId: String? = null,

    @SerializedName("order")
    @Expose
    var order: Int? = null
){
    fun getAvatar(): String{
        return profilePath?.domainLinkImage().toString()
    }
}
