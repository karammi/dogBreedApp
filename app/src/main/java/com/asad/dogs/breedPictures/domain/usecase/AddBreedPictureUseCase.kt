package com.asad.dogs.breedPictures.domain.usecase

import com.asad.dogs.breedPictures.domain.repository.BreedPictureRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class AddBreedPictureUseCase @Inject constructor(
    private val repository: BreedPictureRepository,
) {

    suspend operator fun invoke(breedName: String, breedUrl: String) {
        repository.addBreedPicture(breedName = breedName, breedUrl = breedUrl)
    }
}
