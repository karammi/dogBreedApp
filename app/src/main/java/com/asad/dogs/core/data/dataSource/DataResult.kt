package com.asad.dogs.core.data.dataSource

import com.asad.dogs.core.data.dataSource.remote.model.CustomNetworkException

/**
 * This is a type defined to wrap the data returned from API calls.
 * It could be [DataResult.Error] in case of the API not succeeding and
 * [DataResult.Success] in case of a successful API call.
 *
 * @param value is of a generic type and is different based on each API.
 * @param exception is the one that the api have thrown.
 * */
sealed class DataResult<out R>(
    open val value: R? = null,
    open val exception: CustomNetworkException? = null,
) {
    class Success<T>(override val value: T) : DataResult<T>()
    class Error(override val exception: CustomNetworkException) : DataResult<Nothing>()
}

/**
 * This extension mapper function is used to convert entity objects to domain models
 * using their transform functions
 * */
inline fun <reified R, reified T> DataResult<T>.map(transform: (value: T) -> R): DataResult<R> {
    return when (this) {
        is DataResult.Success -> DataResult.Success(transform.invoke(value))
        else -> DataResult.Error(
            exception ?: CustomNetworkException(
                status = "error",
                message = "error convert exception inside dataResult mapper",
                code = 2023,
            ),
        )
    }
}
