package com.asad.dogs.favoritePictures.di.module

import com.asad.dogs.favoritePictures.data.dataSource.local.FavoritePictureLocalDataSource
import com.asad.dogs.favoritePictures.data.dataSource.local.FavoritePictureLocalDataSourceImpl
import com.asad.dogs.favoritePictures.data.repository.FavoritePictureRepositoryImpl
import com.asad.dogs.favoritePictures.domain.repository.FavoritePictureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class FavoritePictureBinderModule {

    @Binds
    abstract fun bindFavoritePictureLocalDataSource(favoritePictureLocalDataSource: FavoritePictureLocalDataSourceImpl): FavoritePictureLocalDataSource

    @Binds
    abstract fun bindFavoritePictureRepository(favoritePictureRepository: FavoritePictureRepositoryImpl): FavoritePictureRepository
}
