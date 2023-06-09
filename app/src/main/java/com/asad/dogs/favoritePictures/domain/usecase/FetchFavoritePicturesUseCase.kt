package com.asad.dogs.favoritePictures.domain.usecase

import com.asad.dogs.favoritePictures.domain.model.FavoritePictureResponse
import com.asad.dogs.favoritePictures.domain.repository.FavoritePictureRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class FetchFavoritePicturesUseCase @Inject constructor(
    private val repository: FavoritePictureRepository,
) {

    suspend operator fun invoke(): Flow<List<FavoritePictureResponse>> = repository.observeFavoritePictureFlow()

    suspend fun fetchFavoriteBreedByName(breed: String?) = repository.filterFavoritePictureByBreedName(breed)
}
