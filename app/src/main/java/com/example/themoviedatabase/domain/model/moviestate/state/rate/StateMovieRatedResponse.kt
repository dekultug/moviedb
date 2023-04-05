package com.example.themoviedatabase.domain.model.moviestate.state.rate

import com.example.themoviedatabase.domain.model.moviestate.state.Rated
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StateMovieRatedResponse : StateMovie() {
    var rated: Rated? = null
}
