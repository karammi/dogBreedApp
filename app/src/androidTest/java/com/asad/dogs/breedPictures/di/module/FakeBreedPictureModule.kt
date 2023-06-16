package com.asad.dogs.breedPictures.di.module

import com.asad.dogs.breedPictures.data.dataSource.local.dao.BreedPictureDao
import com.asad.dogs.breedPictures.data.dataSource.remote.api.BreedPictureApi
import com.asad.dogs.core.data.dataSource.local.DogBreedDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import retrofit2.Retrofit

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [BreedPictureModule::class],
)
object FakeBreedPictureModule {

    @Provides
    fun provideBreedPictureApi(retrofit: Retrofit): BreedPictureApi =
        retrofit.create(BreedPictureApi::class.java)

    @Provides
    fun provideBreedFavoriteDao(database: DogBreedDatabase): BreedPictureDao =
        database.getFavoriteDao()
}
