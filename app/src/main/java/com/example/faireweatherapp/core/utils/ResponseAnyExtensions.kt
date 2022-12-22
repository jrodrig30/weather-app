package com.example.faireweatherapp.core.utils

import com.example.faireweatherapp.core.network.ResponseAny
import com.example.faireweatherapp.core.network.ResponseEmpty
import com.example.faireweatherapp.core.network.ResponseError
import com.example.faireweatherapp.core.network.ResponseSuccess

fun <T> ResponseAny<T>.read(
    success: (T) -> Unit,
    error: ((Exception) -> Unit)? = null,
    empty: ((Int) -> Unit)? = null
) {
    when (this) {
        is ResponseSuccess -> success(this.body)
        is ResponseError -> error?.invoke(this.exception)
        is ResponseEmpty -> empty?.invoke(this.code)
    }
}
