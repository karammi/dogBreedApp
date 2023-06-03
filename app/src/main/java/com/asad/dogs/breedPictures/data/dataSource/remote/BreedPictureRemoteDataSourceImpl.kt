package com.asad.dogs.breedPictures.data.dataSource.remote

import com.asad.dogs.breedPictures.data.dataSource.remote.api.BreedPictureApi
import com.asad.dogs.breedPictures.data.dataSource.remote.model.BreedPictureResponseModel
import com.asad.dogs.core.util.ApiRunner
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BreedPictureRemoteDataSourceImpl @Inject constructor(
    private val apiRunner: ApiRunner,
    private val breedPictureApi: BreedPictureApi,
) : BreedPictureRemoteDataSource {

    override suspend fun fetchBreedPictures(dogBreedName: String): Result<BreedPictureResponseModel> =
        apiRunner.invokeSuspended {
            breedPictureApi.fetchBreedPictures(dogBreedName)
        }
}
