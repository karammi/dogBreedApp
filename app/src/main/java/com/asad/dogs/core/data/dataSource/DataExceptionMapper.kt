package com.asad.dogs.core.data.dataSource

import com.asad.dogs.core.data.dataSource.remote.model.CustomNetworkException
import com.asad.dogs.core.data.util.CoreString
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import javax.inject.Inject

class DataExceptionMapper @Inject constructor(
    private val retrofitConverter: Converter<ResponseBody, CustomNetworkException>,
) {
    operator fun invoke(e: Exception): CustomNetworkException {
        return when (e) {
            is HttpException -> {
                var errorBody: CustomNetworkException? = null
                try {
                    e.response()?.errorBody()?.let {
                        errorBody = retrofitConverter.convert(it)
                    }
                    if (errorBody != null) {
                        errorBody!!.copy(
                            status = "error",
                            message = errorBody!!.message ?: "",
                            code = e.code(),
                        )
                    } else {
                        CustomNetworkException(
                            status = "error",
                            message = CoreString.UnknownNetworkError,
                            code = 1001,
                        )
                    }
                } catch (ex: Exception) {
                    CustomNetworkException(
                        status = "error",
                        message = CoreString.HTTP_UNKNOWN_ERROR_MESSAGE,
                        code = 1001,
                    )
                }
            }

            else -> {
                CustomNetworkException(
                    status = "error",
                    message = CoreString.HTTP_UNKNOWN_ERROR_MESSAGE,
                    code = 1001,
                )
            }
        }
    }
}
