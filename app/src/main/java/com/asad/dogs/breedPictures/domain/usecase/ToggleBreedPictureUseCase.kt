package com.asad.dogs.breedPictures.domain.usecase

import com.asad.dogs.breedPictures.domain.repository.BreedPictureRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

/**
 * A use case responsible for toggling the favorite status of a dog breed.
 * */
@ViewModelScoped
class ToggleBreedPictureUseCase @Inject constructor(
    private val repository: BreedPictureRepository,
) {

    suspend operator fun invoke(breedName: String, breedUrl: String) {
        repository.toggleBreedPicture(breedName = breedName, breedUrl = breedUrl)
    }
}
