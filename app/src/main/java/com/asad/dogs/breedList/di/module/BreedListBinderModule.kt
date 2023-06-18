package com.asad.dogs.breedList.di.module

import com.asad.dogs.breedList.data.dataSource.local.BreedListLocalDataSource
import com.asad.dogs.breedList.data.dataSource.local.BreedListLocalDataSourceImpl
import com.asad.dogs.breedList.data.dataSource.remote.BreedListRemoteDataSource
import com.asad.dogs.breedList.data.dataSource.remote.BreedListRemoteDataSourceImpl
import com.asad.dogs.breedList.data.repository.BreedListRepositoryImpl
import com.asad.dogs.breedList.domain.repository.BreedListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class BreedListBinderModule {

    @Binds
    abstract fun bindBreedListRemoteDataSource(breedListRemoteDataSource: BreedListRemoteDataSourceImpl): BreedListRemoteDataSource

    @Binds
    abstract fun bindBreedListLocalDataSource(breedListLocalDataSource: BreedListLocalDataSourceImpl): BreedListLocalDataSource

    @Binds
    abstract fun bindBreedListRepository(breedListRepository: BreedListRepositoryImpl): BreedListRepository
}
