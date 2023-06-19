package com.asad.dogs.core.data.dataSource

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    suspend fun <T> invokeSuspendedFlow(job: suspend () -> T): Flow<DataResult<T>> {
        return flow {
            try {
                emit(DataResult.Success(job.invoke()))
            } catch (ex: Exception) {
                emit(DataResult.Error(dataExceptionMapper.invoke(ex)))
            }
        }
    }
}
