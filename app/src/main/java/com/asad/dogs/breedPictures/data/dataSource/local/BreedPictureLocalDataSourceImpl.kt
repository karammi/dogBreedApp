package com.asad.dogs.breedPictures.data.dataSource.local

import com.asad.dogs.breedPictures.data.dataSource.local.dao.BreedPictureDao
import com.asad.dogs.breedPictures.data.dataSource.local.entity.PictureEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class BreedPictureLocalDataSourceImpl @Inject constructor(
    private val breedPictureDao: BreedPictureDao,
) : BreedPictureLocalDataSource {
    override suspend fun toggleBreedPicture(breedName: String, breedUrl: String) {
        breedPictureDao
            .toggleBreedPicture(
                entity = PictureEntity(
//                    title = breedName,
                    breedName = breedName,
                    breedUrl = breedUrl,
                ),
            )
    }

    override suspend fun getFavoriteBreedPictures(name: String): Flow<List<PictureEntity>> {
        return breedPictureDao.getFavoriteBreedPictures(name)
    }
}
