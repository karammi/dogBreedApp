package com.asad.dogs.core.di.module

import com.asad.dogs.core.data.repository.breedFavorite.BreedFavoriteRepositoryImpl
import com.asad.dogs.core.domain.repository.BreedFavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreBinderModule {

    @Binds
    @Singleton
    abstract fun bindBreedFavoriteRepo(breedFavoriteRepository: BreedFavoriteRepositoryImpl): BreedFavoriteRepository
}
