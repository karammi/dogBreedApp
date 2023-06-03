package com.asad.dogs.breedList.data.dataSource.remote

import com.asad.dogs.breedList.data.dataSource.remote.model.DogResponseModel

interface BreedListRemoteDataSource {

    suspend fun fetchBreedList(): Result<DogResponseModel>
}
