package com.asad.dogs.breedPictures.data.dataSource.remote

import com.asad.dogs.breedPictures.data.dataSource.remote.model.BreedPictureResponseModel
import com.asad.dogs.core.data.dataSource.DataResult
import kotlinx.coroutines.flow.Flow

interface BreedPictureRemoteDataSource {

    suspend fun fetchBreedPictures(breedName: String): Flow<DataResult<BreedPictureResponseModel>>
}
