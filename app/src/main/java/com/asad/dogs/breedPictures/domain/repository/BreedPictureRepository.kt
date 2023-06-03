package com.asad.dogs.breedPictures.domain.repository

import com.asad.dogs.breedPictures.data.dataSource.remote.model.BreedPictureResponseModel

interface BreedPictureRepository {

    suspend fun fetchBreedPicture(dogBreedName: String): Result<BreedPictureResponseModel>
}
