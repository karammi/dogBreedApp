package com.asad.dogs.breedList.data.dataSource.local

import com.asad.dogs.breedList.data.dataSource.local.entity.BreedEntity
import kotlinx.coroutines.flow.Flow

interface BreedListLocalDataSource {

    suspend fun getBreeds(): Flow<List<BreedEntity>>

    suspend fun insertBreedList(breeds: List<BreedEntity>)
}
