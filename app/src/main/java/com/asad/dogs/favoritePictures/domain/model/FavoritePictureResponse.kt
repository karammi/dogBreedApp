package com.asad.dogs.favoritePictures.domain.model

data class FavoritePictureResponse(
    val title: String? = null,
    val urls: List<FavoritePictureModel>? = null,
    val isSelected: Boolean = false,
)
