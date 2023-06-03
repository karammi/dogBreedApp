package com.asad.dogs.core.domain.repository

import kotlinx.coroutines.flow.StateFlow

interface BreedFavoriteRepository {

    val breedFavoriteFlow: StateFlow<List<String>?>

    suspend fun addBreedToFavorite(value: String)

    suspend fun removeBreedFromFavorite(value: String)

    suspend fun onBreedPictureClicked(url: String)

    suspend fun clearFavorite()
}
