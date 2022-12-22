package com.example.faireweatherapp.core.network

import androidx.annotation.Keep
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

sealed class ResponseAny<out T> {
    companion object {
        fun <T> create(response: Response<T>): ResponseAny<T> {
            return if (response.isSuccessful) {
                when (val body = response.body()) {
                    null -> ResponseEmpty(response.code())
                    else -> ResponseSuccess(body)
                }

            } else {
                ResponseError(ioException(response))
            }
        }

        fun <T> ioException(response: Response<T>) = IOException(
            "ERROR CODE: ${response.code()} - ERROR MESSAGE: ${response.message()} - ERROR BODY: ${
                response.errorBody()?.string()
            }",

            HttpException(response)
        )

        fun create(exception: Exception): ResponseError = ResponseError(exception)
    }
}

@Keep
data class ResponseEmpty(val code: Int) : ResponseAny<Nothing>()

@Keep
data class ResponseError(val exception: Exception, val errorBody: String? = null) :
    ResponseAny<Nothing>()

@Keep
data class ResponseSuccess<T>(val body: T) : ResponseAny<T>()