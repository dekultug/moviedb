package com.example.themoviedatabase.data.network

import com.example.themoviedatabase.base.exception.APIException
import com.example.themoviedatabase.domain.model.list.movie.MovieResponse
import com.google.gson.Gson
import retrofit2.Response

object ExceptionHelper {

    // SUCCESSFUL
    private const val HTTP_CODE_SUCCESSFUL_OK = 200

    private const val HTTP_403 = 403

    fun throwException(response: Response<*>): APIException {
        return when (response.code()) {
            HTTP_CODE_SUCCESSFUL_OK -> getAPIExceptionWhenHTTPCodeSuccessful(response)
            HTTP_403 -> {
                val gson = Gson()
                val movieResponse = gson.fromJson(response.errorBody()?.string(),MovieResponse::class.java)
                APIException(response.code(),movieResponse.statusMessage)
            }
            else -> APIException(response.code(), response.message())
        }
    }

    private fun getAPIExceptionWhenHTTPCodeSuccessful(response: Response<*>): APIException {
        return APIException(APIException.BODY_NULL)
    }
}
