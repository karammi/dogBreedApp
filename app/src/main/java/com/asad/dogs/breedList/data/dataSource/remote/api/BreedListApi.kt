package com.asad.dogs.breedList.data.dataSource.remote.api

import com.asad.dogs.breedList.data.dataSource.remote.model.DogResponseModel
import retrofit2.http.GET

interface BreedListApi {

    @GET("breeds/list/all")
    suspend fun fetchBreedList(): DogResponseModel
}
