package com.asad.dogs.breedList.data.dataSource.local.dao

import com.asad.dogs.breedList.data.dataSource.local.entity.BreedEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ColdFakeBreedListDao : BreedListDao {

    private val breedList = mutableListOf(
        BreedEntity("akita", ""),
        BreedEntity("amircan", "sub1"),
    )

    override fun getBreeds(): Flow<List<BreedEntity>> {
        return flow { emit(breedList) }
    }

    override suspend fun insertBreed(breed: BreedEntity) {
        breedList.add(breed)
    }

    override suspend fun insertBreedList(breeds: List<BreedEntity>) {
        breedList.addAll(breeds)
    }
}
