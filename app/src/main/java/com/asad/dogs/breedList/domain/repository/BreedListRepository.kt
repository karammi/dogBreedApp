package com.asad.dogs.breedList.domain.repository

import com.asad.dogs.breedList.domain.model.BreedModel
import kotlinx.coroutines.flow.Flow

interface BreedListRepository {

    /**
     * This method fetches a list of the breeds and emit them using a flow
     * */
    suspend fun fetchBreeds(): Flow<List<BreedModel>>
}
