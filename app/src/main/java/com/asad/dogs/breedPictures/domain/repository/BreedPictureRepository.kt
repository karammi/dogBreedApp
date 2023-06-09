package com.asad.dogs.breedPictures.domain.repository

import com.asad.dogs.breedPictures.domain.model.BreedPictureResponse
import com.asad.dogs.core.data.dataSource.DataResult
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface BreedPictureRepository {

    val favoritePicturesFlow: StateFlow<DataResult<List<FavoritePictureModel>>?>

    suspend fun fetchBreedPictures(breedName: String)

    suspend fun addBreedPicture(breedName: String, breedUrl: String)
}
