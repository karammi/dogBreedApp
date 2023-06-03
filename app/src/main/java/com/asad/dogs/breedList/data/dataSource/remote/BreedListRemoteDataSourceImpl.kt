package com.asad.dogs.breedList.data.dataSource.remote

import com.asad.dogs.breedList.data.dataSource.remote.api.BreedListApi
import com.asad.dogs.breedList.data.dataSource.remote.model.DogResponseModel
import com.asad.dogs.core.util.ApiRunner
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BreedListRemoteDataSourceImpl @Inject constructor(
    private val breedListApi: BreedListApi,
    private val apiRunner: ApiRunner,
) : BreedListRemoteDataSource {

    override suspend fun fetchBreedList(): Result<DogResponseModel> =
        apiRunner.invokeSuspended {
            breedListApi.fetchBreedList()
        }
}
