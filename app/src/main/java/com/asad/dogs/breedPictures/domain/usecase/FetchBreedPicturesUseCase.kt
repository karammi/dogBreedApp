package com.asad.dogs.breedPictures.domain.usecase

import com.asad.dogs.breedPictures.domain.model.BreedPictureResponse
import com.asad.dogs.breedPictures.domain.repository.BreedPictureRepository
import com.asad.dogs.core.data.dataSource.DataResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class FetchBreedPicturesUseCase @Inject constructor(
    private val repository: BreedPictureRepository,
) {

    suspend operator fun invoke(breed: String): DataResult<BreedPictureResponse> {
        return repository.fetchBreedPictures(breedName = breed)
    }
}
