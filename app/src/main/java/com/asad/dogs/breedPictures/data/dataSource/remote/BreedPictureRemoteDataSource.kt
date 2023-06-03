package com.asad.dogs.breedPictures.data.dataSource.remote

import com.asad.dogs.breedPictures.data.dataSource.remote.model.BreedPictureResponseModel

interface BreedPictureRemoteDataSource {

    suspend fun fetchBreedPictures(dogBreedName: String): Result<BreedPictureResponseModel>
}
