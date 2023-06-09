package com.asad.dogs.favoritePictures.data.dataSource.local.entity

import androidx.room.Entity

@Entity
data class FavoritePictureEntity(
    val breedName: String,
    val breedUrl: String,
)
