package com.asad.dogs.favoritePictures.data.dataSource.local

import com.asad.dogs.favoritePictures.data.dataSource.local.model.FavoritePictureResponseModel
import kotlinx.coroutines.flow.StateFlow

interface FavoritePictureLocalDataSource {

    val favoriteBreedFlow: StateFlow<List<FavoritePictureResponseModel>>

    suspend fun fetchFavoritePicturesByBreedName(breed: String?)
    suspend fun loadFavoritePictures()

    suspend fun fetchFavoritePictures(): List<FavoritePictureResponseModel>
}
