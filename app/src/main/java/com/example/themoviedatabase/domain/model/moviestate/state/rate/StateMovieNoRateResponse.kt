package com.example.themoviedatabase.domain.model.moviestate.state.rate

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StateMovieNoRateResponse : StateMovie() {
    var noRate: Boolean? = null
}
