package com.example.themoviedatabase.domain.model.trending.detail.review

import com.example.themoviedatabase.common.INT_DEFAULT
import com.example.themoviedatabase.common.extension.STRING_DEFAULT
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class InfoReview(
    @SerializedName("author")
    @Expose
    var author: String? = null,

    @SerializedName("author_details")
    @Expose
    var authorDetails: AuthorDetails? = null,

    @SerializedName("content")
    @Expose
    var content: String? = null,

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,

    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null
) {
    fun getAvatarUser(): String {
        return authorDetails?.getAvatar() ?: STRING_DEFAULT
    }

    fun getNameUser(): String {
        return authorDetails?.name ?: authorDetails?.username ?: author ?: "no name"
    }

    fun getDateCommented(): String {
        return createdAt ?: STRING_DEFAULT
    }

    fun getLengthContent(): Int{
        return if (content != null){
            content!!.length
        }else{
            INT_DEFAULT
        }
    }
}
