package com.asad.dogs.breedList.data.dataSource.remote

import com.asad.dogs.breedList.data.dataSource.remote.api.BreedListApi
import com.asad.dogs.breedList.data.dataSource.remote.model.BreedResponseModel
import com.asad.dogs.core.data.dataSource.ApiRunner
import com.asad.dogs.core.data.dataSource.DataResult
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * An implementation of the repository interface, responsible for fetching data from the data source
 *
 * */
@ViewModelScoped
class BreedListRemoteDataSourceImpl @Inject constructor(
    private val breedsApi: BreedListApi,
    private val apiRunner: ApiRunner,
) : BreedListRemoteDataSource {

    override suspend fun fetchBreedList(): Flow<DataResult<BreedResponseModel>> =
        apiRunner.invokeSuspendedFlow { breedsApi.fetchBreedList() }
}
