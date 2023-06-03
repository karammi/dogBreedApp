package com.asad.dogs.breedPictures.data.repository

import com.asad.dogs.breedPictures.data.dataSource.remote.BreedPictureRemoteDataSource
import com.asad.dogs.breedPictures.data.dataSource.remote.model.BreedPictureResponseModel
import com.asad.dogs.breedPictures.domain.repository.BreedPictureRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BreedPictureRepositoryImpl @Inject constructor(
    private val breedPictureRemoteDataSource: BreedPictureRemoteDataSource,
) : BreedPictureRepository {

    override suspend fun fetchBreedPicture(dogBreedName: String): Result<BreedPictureResponseModel> =
        breedPictureRemoteDataSource
            .fetchBreedPictures(dogBreedName = dogBreedName)
}
