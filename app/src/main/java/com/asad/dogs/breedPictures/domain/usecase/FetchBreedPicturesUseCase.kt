package com.asad.dogs.breedPictures.domain.usecase

import com.asad.dogs.breedPictures.domain.repository.BreedPictureRepository
import com.asad.dogs.core.data.dataSource.DataResult
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class FetchBreedPicturesUseCase @Inject constructor(
    private val repository: BreedPictureRepository,
) {

    fun observeDataFlow(): Flow<DataResult<List<FavoritePictureModel>>?> =
        repository.favoritePicturesFlow

    suspend operator fun invoke(breed: String) {
        repository.fetchBreedPictures(breedName = breed)
    }
}
