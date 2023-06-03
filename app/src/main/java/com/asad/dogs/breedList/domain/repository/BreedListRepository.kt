package com.asad.dogs.breedList.domain.repository

import com.asad.dogs.breedList.domain.model.DogModel

interface BreedListRepository {

    suspend fun fetchBreedList(): Result<DogModel>
}
