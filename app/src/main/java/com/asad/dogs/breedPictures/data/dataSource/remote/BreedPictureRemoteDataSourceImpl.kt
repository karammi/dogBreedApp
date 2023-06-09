package com.asad.dogs.breedPictures.data.dataSource.remote

import com.asad.dogs.breedPictures.data.dataSource.remote.api.BreedPictureApi
import com.asad.dogs.breedPictures.data.dataSource.remote.model.BreedPictureResponseModel
import com.asad.dogs.core.data.dataSource.ApiRunner
import com.asad.dogs.core.data.dataSource.DataResult
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class BreedPictureRemoteDataSourceImpl @Inject constructor(
    private val apiRunner: ApiRunner,
    private val pictureApi: BreedPictureApi,
) : BreedPictureRemoteDataSource {

    override suspend fun fetchBreedPictures(breedName: String): Flow<DataResult<BreedPictureResponseModel>> =
        flow {
            emit(
                apiRunner.invokeSuspended {
                    pictureApi.fetchBreedPictures(breedName)
                },
            )
        }
}
