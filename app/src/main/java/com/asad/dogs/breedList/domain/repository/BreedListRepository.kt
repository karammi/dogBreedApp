package com.asad.dogs.breedList.domain.repository

import com.asad.dogs.breedList.domain.model.BreedModel
import kotlinx.coroutines.flow.Flow

/**
 * A repository interface that defines methods for retrieving and managing dog breed data.
 * */
interface BreedListRepository {

    suspend fun fetchBreeds(): Flow<List<BreedModel>>
}
