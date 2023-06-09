package com.asad.dogs.breedPictures.data.dataSource.remote

import com.asad.dogs.breedPictures.data.dataSource.remote.model.BreedPictureResponseModel
import com.asad.dogs.core.data.dataSource.DataResult

interface BreedPictureRemoteDataSource {

    suspend fun fetchBreedPictures(breedName: String): DataResult<BreedPictureResponseModel>
}
