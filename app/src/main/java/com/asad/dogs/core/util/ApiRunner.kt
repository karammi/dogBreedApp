package com.asad.dogs.core.util

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ApiRunner @Inject constructor(
    private val dataExceptionMapper: DataExceptionMapper,
) {
    suspend fun <T> invokeSuspended(job: suspend () -> T): Result<T> {
        return try {
            Result.success(job.invoke())
        } catch (ex: Exception) {
            Result.failure(ex)
//            Result.failure(dataExceptionMapper.invoke(ex))
        }
    }
}
