package com.asad.dogs.core.data.dataSource.remote.model

import com.squareup.moshi.JsonClass

/**
 * This is a custom exception type used in the [DataExceptionMapper] so that
 * the code wouldn't be dependant to a specific HTTP client exception type.
 * those kind of exceptions in the [ApiRunner] will be caught and mapped to this type.
 * */
@JsonClass(generateAdapter = true)
data class CustomNetworkException(
    val status: String,
    val message: String?,
    val code: Int,
)
