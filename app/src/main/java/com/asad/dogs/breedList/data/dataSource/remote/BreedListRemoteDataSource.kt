package com.asad.dogs.breedList.data.dataSource.remote

import com.asad.dogs.breedList.data.dataSource.remote.model.BreedResponseModel
import com.asad.dogs.core.data.dataSource.DataResult

interface BreedListRemoteDataSource {

    suspend fun fetchBreedList(): DataResult<BreedResponseModel>
}
