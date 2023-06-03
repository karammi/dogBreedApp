package com.asad.dogs.core.util

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class DataExceptionMapper @Inject constructor() {
    operator fun invoke(e: Exception): String {
        return when (e) {
            is HttpException -> "CoreString.buildErrorMessageBody(CoreString.HTTP_EXCEPTION)"
            is SocketTimeoutException -> "CoreString.buildErrorMessageBody(CoreString.SOCKET_TIMEOUT_EXCEPTION)"
            is UnknownHostException -> " CoreString.buildErrorMessageBody(CoreString.UNKNOWN_HOST_ERROR)"
            is CancellationException -> "CoreString.buildErrorMessageBody(CoreString.CANCELLATION_EXCEPTION)"
            is IOException -> "CoreString.buildErrorMessageBody(CoreString.IO_EXCEPTION)"
            else -> "CoreString.buildErrorMessageBody(CoreString.UNKNOWN_ERROR)"
        }
    }
}


/*{
    "status": "error",
    "message": "Breed not found (master breed does not exist)",
    "code": 404
}*/