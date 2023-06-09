package com.asad.dogs.favoritePictures.domain.repository

import com.asad.dogs.favoritePictures.domain.model.FavoritePictureResponse
import kotlinx.coroutines.flow.Flow

interface FavoritePictureRepository {
    suspend fun observeFavoritePictureFlow(): Flow<List<FavoritePictureResponse>>
    suspend fun filterFavoritePictureByBreedName(breed: String?)

    suspend fun fetchSelectedBreeds(): List<FavoritePictureResponse>
}
