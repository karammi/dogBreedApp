package com.asad.dogs.breedList.data.dataSource.remote

import com.asad.dogs.breedList.data.dataSource.remote.model.BreedResponseModel
import com.asad.dogs.core.data.dataSource.DataResult
import kotlinx.coroutines.flow.Flow

/**
 * A data source that interacts with the API client to fetch data from the server.
 * */
interface BreedListRemoteDataSource {

    suspend fun fetchBreedList(): Flow<DataResult<BreedResponseModel>>
}
