package com.asad.dogs.breedPictures.domain.repository

import com.asad.dogs.core.data.dataSource.DataResult
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureModel
import kotlinx.coroutines.flow.StateFlow

interface BreedPictureRepository {

    val favoritePicturesFlow: StateFlow<DataResult<List<FavoritePictureModel>>?>

    suspend fun fetchBreedPictures(breedName: String)

    suspend fun toggleBreedPicture(breedName: String, breedUrl: String)
}
