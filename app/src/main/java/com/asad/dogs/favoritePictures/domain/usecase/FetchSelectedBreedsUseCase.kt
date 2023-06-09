package com.asad.dogs.favoritePictures.domain.usecase

import com.asad.dogs.favoritePictures.domain.model.FavoritePictureResponse
import com.asad.dogs.favoritePictures.domain.repository.FavoritePictureRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class FetchSelectedBreedsUseCase @Inject constructor(
    private val repository: FavoritePictureRepository,
) {

    suspend operator fun invoke(): List<FavoritePictureResponse> = repository.fetchSelectedBreeds()
}
