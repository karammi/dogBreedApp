package com.asad.dogs.breedPictures.data.mapper

import com.asad.dogs.breedPictures.data.dataSource.local.entity.PictureEntity
import com.asad.dogs.core.data.mapper.CustomMapper
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DatabasePictureEntityToModelMapper @Inject constructor() :
    CustomMapper<PictureEntity, FavoritePictureModel> {
    override fun mapTo(entity: PictureEntity): FavoritePictureModel {
        return with(entity) {
            FavoritePictureModel(
                breedName = breedName,
                breedUrl = breedUrl,
                isFavorite = false,
            )
        }
    }
}
