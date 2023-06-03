package com.asad.dogs.breedPictures.di.module

import com.asad.dogs.breedPictures.data.dataSource.remote.api.BreedPictureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object BreedPictureModule {

    @Provides
    fun provideBreedPictureApi(retrofit: Retrofit): BreedPictureApi =
        retrofit.create(BreedPictureApi::class.java)
}
