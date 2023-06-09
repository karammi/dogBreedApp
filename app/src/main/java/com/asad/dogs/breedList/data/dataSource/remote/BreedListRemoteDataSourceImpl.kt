package com.asad.dogs.breedList.data.dataSource.remote

import com.asad.dogs.breedList.data.dataSource.remote.api.BreedListApi
import com.asad.dogs.breedList.data.dataSource.remote.model.BreedResponseModel
import com.asad.dogs.core.data.dataSource.ApiRunner
import com.asad.dogs.core.data.dataSource.DataResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BreedListRemoteDataSourceImpl @Inject constructor(
    private val breedsApi: BreedListApi,
    private val apiRunner: ApiRunner,
) : BreedListRemoteDataSource {

    override suspend fun fetchBreedList(): DataResult<BreedResponseModel> =
        apiRunner.invokeSuspended { breedsApi.fetchBreedList() }
}
