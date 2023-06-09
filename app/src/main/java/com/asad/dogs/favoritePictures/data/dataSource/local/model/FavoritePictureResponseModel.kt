package com.asad.dogs.favoritePictures.data.dataSource.local.model

import com.asad.dogs.favoritePictures.data.dataSource.local.entity.FavoritePictureEntity

data class FavoritePictureResponseModel(
    val title: String? = null,
    val urls: List<FavoritePictureEntity>? = null,
    val isSelected: Boolean = false,
)
