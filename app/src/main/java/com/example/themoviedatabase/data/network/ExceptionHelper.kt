package com.example.themoviedatabase.data.network

import com.example.themoviedatabase.base.exception.APIException
import retrofit2.Response

object ExceptionHelper {

    // SUCCESSFUL
    private const val HTTP_CODE_SUCCESSFUL_OK = 200

    fun throwException(response: Response<*>): APIException {
        return when (response.code()) {
            HTTP_CODE_SUCCESSFUL_OK -> getAPIExceptionWhenHTTPCodeSuccessful(response)
            else -> APIException(response.code(), response.message())
        }
    }

    private fun getAPIExceptionWhenHTTPCodeSuccessful(response: Response<*>): APIException {
        return APIException(APIException.BODY_NULL)
    }
}
