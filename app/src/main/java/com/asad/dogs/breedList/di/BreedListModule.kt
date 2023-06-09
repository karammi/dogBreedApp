package com.asad.dogs.breedList.di

import com.asad.dogs.breedList.data.dataSource.local.dao.BreedListDao
import com.asad.dogs.breedList.data.dataSource.remote.api.BreedListApi
import com.asad.dogs.core.data.dataSource.local.DogBreedDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object BreedListModule {

    @Provides
    fun provideBreedListApi(retrofit: Retrofit): BreedListApi =
        retrofit.create(BreedListApi::class.java)

    @Provides
    fun provideBreedListDao(database: DogBreedDatabase): BreedListDao = database.getBreedListDao()
}
