package com.example.themoviedatabase.data.network

import com.example.themoviedatabase.base.exception.APIException
import com.example.themoviedatabase.base.repo.BaseRepo
import okhttp3.Headers
import retrofit2.Call
import java.net.UnknownHostException

fun <RESPONSE : IApiResponse, RETURN_VALUE> Call<RESPONSE>.invokeApi(
    block: (Headers, RESPONSE, String) -> RETURN_VALUE
): RETURN_VALUE {
    try {
        val response = this.execute()
        val json = response.body().toString()
        if (response.isSuccessful) {
            val body: RESPONSE? = response.body()
            if (body != null) {
                return block(response.headers(), body,json)
            }
        }
        throw  ExceptionHelper.throwException(response)
    } catch (e: Exception) {
        when (e) {
            is UnknownHostException -> throw APIException(APIException.API_FAIL)
            else -> throw e
        }
    }
}

fun <T : IApiService> BaseRepo.invokeService(service: Class<T>): T {
    return ClientConnectServer.createService(service)
}

