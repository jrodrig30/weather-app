package com.example.faireweatherapp.core.network

import retrofit2.Response
import java.lang.Exception

suspend fun <T> apiCall(call: suspend () -> Response<T>): ResponseAny<T> {
    return try {
        ResponseAny.create(call())
    } catch (exception: Exception) {
        ResponseAny.create(exception)
    }
}