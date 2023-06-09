package com.asad.dogs.breedPictures.domain.repository

import com.asad.dogs.breedPictures.domain.model.BreedPictureResponse
import com.asad.dogs.core.data.dataSource.DataResult

interface BreedPictureRepository {

    suspend fun fetchBreedPictures(breedName: String): DataResult<BreedPictureResponse>

    suspend fun addBreedPicture(breedName: String, breedUrl: String)
}
