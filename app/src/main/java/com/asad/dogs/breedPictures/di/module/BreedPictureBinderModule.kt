package com.asad.dogs.breedPictures.di.module

import com.asad.dogs.breedPictures.data.dataSource.remote.BreedPictureRemoteDataSource
import com.asad.dogs.breedPictures.data.dataSource.remote.BreedPictureRemoteDataSourceImpl
import com.asad.dogs.breedPictures.data.repository.BreedPictureRepositoryImpl
import com.asad.dogs.breedPictures.domain.repository.BreedPictureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class BreedPictureBinderModule {

    @Binds
    abstract fun bindBreedPictureRemoteDataSource(breedPictureRemoteDataSource: BreedPictureRemoteDataSourceImpl):
        BreedPictureRemoteDataSource

    @Binds
    abstract fun bindBreedPictureRepository(breedPictureRepository: BreedPictureRepositoryImpl): BreedPictureRepository
}
