package com.asad.dogs.breedPictures.data.dataSource.local

import com.asad.dogs.breedPictures.data.dataSource.local.entity.PictureEntity
import kotlinx.coroutines.flow.Flow

interface BreedPictureLocalDataSource {

    suspend fun toggleBreedPicture(breedName: String, breedUrl: String)

    suspend fun getFavoriteBreedPictures(name: String): Flow<List<PictureEntity>>
}
