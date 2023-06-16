package com.asad.dogs.favoritePictures.di.module

import com.asad.dogs.core.data.dataSource.local.DogBreedDatabase
import com.asad.dogs.favoritePictures.data.dataSource.local.dao.FavoritePicturesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [FavoritePictureModule::class],
)
object FakeFavoritePictureModule {
    @Provides
    fun provideFavoriteDao(database: DogBreedDatabase): FavoritePicturesDao = database.getFavorite()
}
