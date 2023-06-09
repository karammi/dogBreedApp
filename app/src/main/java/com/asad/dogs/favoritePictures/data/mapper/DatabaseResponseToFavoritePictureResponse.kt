package com.asad.dogs.favoritePictures.data.mapper

import com.asad.dogs.core.data.mapper.CustomMapper
import com.asad.dogs.favoritePictures.data.dataSource.local.model.FavoritePictureResponseModel
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureResponse
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DatabaseResponseToFavoritePictureResponse @Inject constructor(
    private val mapper: FavoritePictureEntityToModelMapper,
) : CustomMapper<FavoritePictureResponseModel, FavoritePictureResponse> {
    override fun mapTo(entity: FavoritePictureResponseModel): FavoritePictureResponse {
        return FavoritePictureResponse(
            title = entity.title,
            urls = entity.urls?.map { mapper.mapTo(it) } ?: emptyList(),
            isSelected = entity.isSelected,
        )
    }
}
