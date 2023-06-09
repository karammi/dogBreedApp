package com.asad.dogs.favoritePictures.data.mapper

import com.asad.dogs.core.data.mapper.CustomMapper
import com.asad.dogs.favoritePictures.data.dataSource.local.entity.FavoritePictureEntity
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class FavoritePictureEntityToModelMapper @Inject constructor() :
    CustomMapper<FavoritePictureEntity, FavoritePictureModel> {
    override fun mapTo(entity: FavoritePictureEntity): FavoritePictureModel {
        return with(entity) {
            FavoritePictureModel(breedName = breedName, breedUrl = breedUrl)
        }
    }
}
