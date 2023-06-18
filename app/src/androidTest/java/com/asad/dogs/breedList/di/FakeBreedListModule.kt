package com.asad.dogs.breedList.di

import com.asad.dogs.breedList.data.dataSource.local.dao.BreedListDao
import com.asad.dogs.breedList.data.dataSource.remote.api.BreedListApi
import com.asad.dogs.breedList.di.module.BreedListModule
import com.asad.dogs.core.data.dataSource.local.DogBreedDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import retrofit2.Retrofit

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [BreedListModule::class],
)
object FakeBreedListModule {

    @Provides
    fun provideBreedListApi(retrofit: Retrofit): BreedListApi =
        retrofit.create(BreedListApi::class.java)

    @Provides
    fun provideBreedListDao(database: DogBreedDatabase): BreedListDao = database.getBreedListDao()
}
