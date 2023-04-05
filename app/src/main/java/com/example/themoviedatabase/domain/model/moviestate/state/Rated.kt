package com.example.themoviedatabase.domain.model.moviestate.state

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rated(

    @SerializedName("value")
    @Expose
    var value: Float? = null

)

