package com.asad.dogs.breedPictures.data.dataSource.local

interface BreedPictureLocalDataSource {

    suspend fun addBreedPicture(breedName: String, breedUrl: String)
}
