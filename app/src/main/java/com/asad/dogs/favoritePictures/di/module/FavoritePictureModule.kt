package com.asad.dogs.favoritePictures.di.module

import com.asad.dogs.favoritePictures.data.dataSource.local.dao.FavoritePicturesDao
import com.asad.dogs.core.data.dataSource.local.DogBreedDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object FavoritePictureModule {

    @Provides
    fun provideFavoriteDao(database: DogBreedDatabase): FavoritePicturesDao = database.getFavorite()
}
