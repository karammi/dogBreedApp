package com.asad.dogs.breedPictures.data.dataSource.local

import com.asad.dogs.breedPictures.data.dataSource.local.dao.BreedPictureDao
import com.asad.dogs.breedPictures.data.dataSource.local.entity.PictureEntity
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BreedPictureLocalDataSourceImpl @Inject constructor(
    private val breedPictureDao: BreedPictureDao,
) : BreedPictureLocalDataSource {
    override suspend fun addBreedPicture(breedName: String, breedUrl: String) {
        breedPictureDao
            .addOrDeleteBreedPicture(
                entity = PictureEntity(
                    breedName = breedName,
                    breedUrl = breedUrl,
                ),
            )
    }
}
