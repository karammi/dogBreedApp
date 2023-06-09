package com.asad.dogs.core.data.dataSource

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ApiRunner @Inject constructor(
    private val dataExceptionMapper: DataExceptionMapper,
) {
    suspend fun <T> invokeSuspended(job: suspend () -> T): DataResult<T> {
        return try {
            DataResult.Success(job.invoke())
        } catch (ex: Exception) {
            DataResult.Error(dataExceptionMapper.invoke(ex))
        }
    }
}
