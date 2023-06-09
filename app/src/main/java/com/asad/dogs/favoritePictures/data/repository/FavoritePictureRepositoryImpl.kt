package com.asad.dogs.favoritePictures.data.repository

import com.asad.dogs.favoritePictures.data.dataSource.local.FavoritePictureLocalDataSource
import com.asad.dogs.favoritePictures.data.mapper.DatabaseResponseToFavoritePictureResponse
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureResponse
import com.asad.dogs.favoritePictures.domain.repository.FavoritePictureRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class FavoritePictureRepositoryImpl @Inject constructor(
    private val localDataSource: FavoritePictureLocalDataSource,
    private val mapper: DatabaseResponseToFavoritePictureResponse,
) : FavoritePictureRepository {
    override suspend fun observeFavoritePictureFlow(): Flow<List<FavoritePictureResponse>> =
        localDataSource.favoriteBreedFlow
            .map { favoritePictureList -> favoritePictureList.map { mapper.mapTo(it) } }

    override suspend fun filterFavoritePictureByBreedName(breed: String?) {
        localDataSource.fetchFavoritePicturesByBreedName(breed = breed)
    }

    override suspend fun fetchSelectedBreeds(): List<FavoritePictureResponse> =
        localDataSource.fetchFavoritePictures().map { mapper.mapTo(it) }
}
